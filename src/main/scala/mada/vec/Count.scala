

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Count {
    def apply[A](v: Vector[A], p: A => Boolean): Long = {
        val (first, last) = v.toPair
        stl.CountIf(v, first, last, p)
    }
}
