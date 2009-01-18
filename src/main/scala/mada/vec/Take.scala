

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Take {
    def apply[A](v: Vector[A], n: Long): Vector[A] = {
        v.window(0, Math.min(n, v.size))
    }
}

object TakeWhile {
    def apply[A](v: Vector[A], p: A => Boolean): Vector[A] = {
        val (first, last) = v.pair
        v.window(first, stl.FindIf(v, first, last, !p(_: A)))
    }
}
