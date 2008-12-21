

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.stl


object CountIf {
    def apply[A](v: Vector[A], p: A => Boolean): Long = {
        var c = 0L
        v.foreach({ (e: A) => if (p(e)) c += 1 })
        c
    }
}
