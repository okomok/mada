

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Find {
    def apply[A](v: Vector[A], p: A => Boolean): Option[A] = {
        val __last = v.size

        var __first  = v.stlFindIf(0, __last, p)
        if (__first == __last) {
            None
        } else {
            Some(v(__first))
        }
    }
}
