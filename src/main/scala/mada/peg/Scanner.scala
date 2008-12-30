

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Scanner {
}


trait Scanner[A] extends Vector[A] {
    def copy[B](v: Vector[B]): Scanner[B]
}
