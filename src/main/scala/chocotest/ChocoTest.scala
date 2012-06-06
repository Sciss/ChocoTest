package chocotest

import choco.Choco
import choco.kernel.model.variables.integer.{IntegerVariable, IntegerExpressionVariable}
import choco.kernel.solver.Solver
import choco.kernel.model.constraints.Constraint
import choco.cp.model.CPModel
import choco.cp.solver.CPSolver

// some nicer ways of expressing constraints
class IntExpVarWrapper( iv: IntegerExpressionVariable ) {
   def !==( b: IntegerExpressionVariable ) = Choco.neq( iv, b )
   def ===( b: IntegerExpressionVariable ) = Choco.eq( iv, b )
   def ===( b: Int ) = Choco.eq( iv, b )
}

class IntVarWrapper( iv: IntegerVariable ) extends IntExpVarWrapper( iv ) {
   def solved( implicit s: Solver ) = s.getVar( iv ).getVal
}

// some pimps, and additional constraints adding methods
trait Implicits {
   implicit def wrapIntExpVar( iv: IntegerExpressionVariable ) = new IntExpVarWrapper( iv )
   implicit def wrapIntVar( iv: IntegerVariable ) = new IntVarWrapper( iv )
   def sum( ivs: Seq[ IntegerVariable ]) = Choco.sum( ivs :_ * )
   def cons( c: Constraint )( implicit m: CPModel ) { m.addConstraint( c )}
}

// actual example: magic square
object ChocoTest extends App with Implicits {

   implicit val m = new CPModel
   implicit val s = new CPSolver

   val n = 3
   val n_sqr = n * n
   val magicSum = n * (n_sqr + 1) / 2
   val iv = (0 until n).map { i =>
      (0 until n).map { j =>
         Choco.makeIntVar( "var_" + i + "_" + j, 1, n_sqr )
      }
   }

   for( i <- 0 until n_sqr; j <- i+1 until n_sqr ) {
      cons( iv( i / n )( i % n ) !== iv( j / n )( j % n ))
   }
   for( i <- 0 until n ) {
      cons( sum( iv( i )) === magicSum )
   }
   for( i <- 0 until n ) {
      val col = (0 until n).map( iv( _ )( i ))
      cons( sum( col ) === magicSum )
   }
   val diag1 = (0 until n).map( i => iv( i )( i ))
   val diag2 = (0 until n).map( i => iv( n - 1 - i )( i ))
   cons( sum( diag1 ) === magicSum )
   cons( sum( diag2 ) === magicSum )

   s.read( m )
   s.solve

   for( i <- 0 until n ) println( iv( i ).map( _.solved ).mkString( " " ))
}