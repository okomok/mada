

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector


private[mada] object Foreach {
    def apply[A](v: Vector[A], f: A => Unit): Unit = {
        stl.ForEach(v, v.start, v.end, f)
        ()
    }
}
