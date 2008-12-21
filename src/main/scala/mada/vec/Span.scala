

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Span {
    def apply[A](v : Vector[A], p: A => Boolean): (Vector[A], Vector[A]) = {
        val middle = v.stlFindIf(0, v.size, !p(_: A))
        (v.window(0, middle), v.window(middle, v.size))
    }
}
