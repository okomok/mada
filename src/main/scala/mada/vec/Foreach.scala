

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Foreach {
    def apply[A](v: Vector[A], f: A => Unit): Unit = {
        val (x, first, last) = v.triple
        stl.ForEach(x, first, last, f)
        ()
    }
}
