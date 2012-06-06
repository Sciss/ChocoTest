A simple test combining [Choco Solver](http://www.emn.fr/z-info/choco-solver/) (constraints programming) with the Scala programming language.

Taken from [this scala user thread](http://www.scala-lang.org/node/5558), and updated to work with Scala 2.9:

 - `eq` is now a Scala method, thus must be explicitly referred to as `Choco.eq`
 - the `sum` overload shadows the static Java method, thus again explicitly `Choco.sum`

A basic sbt file is included. It should automatically download Choco 2.1.4 from SourceForge. To run the example: `sbt run`.

This project is placed in the public domain.

---

Other Scala/CP projects I'm aware of (in random order):

 - [Copris](http://bach.istc.kobe-u.ac.jp/copris/)
 - [JaCoP](http://jacop.osolpro.com/) -- page links to the Scala front-end
 - [ScalaSMT](http://code.google.com/p/scalasmt/)
 - [scream](https://github.com/nathanial/scream)
 - [SConSolver](https://github.com/kjellwinblad/sconsolver)
 - [Scampi](https://bitbucket.org/pschaus/scampi/wiki/Home)
 - [Asteroid](http://sourceforge.net/p/asteroid/wiki/Home/)
 - [Kaplan](http://lara.epfl.ch/w/kaplan) -- not a library but a language extension
