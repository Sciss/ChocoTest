A simple test combining [Choco Solver](http://www.emn.fr/z-info/choco-solver/) with the Scala programming language.

Taken from [this scala user thread](http://www.scala-lang.org/node/5558), and updated to work with Scala 2.9:

 - `eq` is now a Scala method, thus must be explicitly referred to as `Choco.eq`
 - the `sum` overload shadows the static Java method, thus again explicitly `Choco.sum`

A basic sbt file is included. It should automatically download Choco 2.1.4 from SourceForge. To run the example: `sbt run`.

This project is placed in the public domain.

