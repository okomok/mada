

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Drop {
    def apply[A](v : Vector[A], n: Long): Vector[A] = {
        val __last = v.size
        v.window(Math.min(n, __last), __last)
    }
}

object DropWhile {
    def apply[A](v : Vector[A], p: A => Boolean): Vector[A] = {
        val (__first, __last) = v.toPair
        v.window(v.stlFindIf(__first, __last, !p(_: A)), __last)
    }
}
