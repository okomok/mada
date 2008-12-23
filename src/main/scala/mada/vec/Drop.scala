

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Drop {
    def apply[A](v: Vector[A], n: Long): Vector[A] = {
        val m = v.size
        v.window(Math.min(n, m), m)
    }
}

object DropWhile {
    def apply[A](v: Vector[A], p: A => Boolean): Vector[A] = {
        val (first, last) = v.toPair
        v.window(stl.FindIf(v, first, last, !p(_: A)), last)
    }
}
