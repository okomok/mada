

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Span {
    def apply[A](v: Vector[A], p: A => Boolean): (Vector[A], Vector[A]) = {
        val (x, first, last) = v.triple
        val middle = stl.FindIf(x, first, last, !p(_: A))
        (x.window(first, middle), x.window(middle, last))
    }
}
