

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Drop {
    def apply[A](v: Vector[A], n: Int): Vector[A] = {
        val (_, last) = v.pair
        v.window(Math.min(n, last), last)
    }
}

object DropWhile {
    def apply[A](v: Vector[A], p: A => Boolean): Vector[A] = {
        val (first, last) = v.pair
        v.window(stl.FindIf(v, first, last, !p(_: A)), last)
    }
}
