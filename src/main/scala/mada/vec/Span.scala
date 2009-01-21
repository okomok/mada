

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Span {
    def apply[A](v: Vector[A], p: A => Boolean): (Vector[A], Vector[A]) = {
        val (x, i, j) = v.triple
        val middle = stl.FindIf(x, i, j, !p(_: A))
        (x.window(i, middle), x.window(middle, j))
    }
}
