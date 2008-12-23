

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Replace {
    def apply[A](v: Vector[A], f: A => A): Vector[A] = {
        val (first, last) = v.toPair
        stl.Transform(v, first, last, v, first, f)
        v
    }
}
