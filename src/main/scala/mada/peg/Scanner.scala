

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Scanner {
    def default[A](v: Vector[A]): Scanner[A] = scanner.Default(v)
}


trait Scanner[A] extends Vector[A] {
    def copy[B](v: Vector[B]): Scanner[B]
    def isActionable: Boolean
    def setActionable(b: Boolean): Unit
}
