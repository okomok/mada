

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Take {
    def apply[A](v: Vector[A], n: Int): Vector[A] = {
        v.window(0, Math.min(n, v.size))
    }
}

object TakeWhile {
    def apply[A](v: Vector[A], p: A => Boolean): Vector[A] = {
        val (x, i, j) = v.triple
        x.window(i, stl.FindIf(x, i, j, !p(_: A)))
    }
}
