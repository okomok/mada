

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Foreach {
    def apply[A](v : Vector[A], f: A => Unit): Unit = {
        val (first, last) = v.toPair
        stl.ForEach(v, first, last, f)
        ()
    }
}
