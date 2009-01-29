

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Span {
    def apply[A](v: Vector[A], p: A => Boolean): (Vector[A], Vector[A]) = {
        val middle = stl.FindIf(v, v.start, v.end, Functions.not(p))
        (v(v.start, middle), v(middle, v.end))
    }
}
