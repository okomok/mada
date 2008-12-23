

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Find {
    def apply[A](* : Vector[A], p: A => Boolean): Option[A] = {
        var (first, last) = *.toPair

        first = stl.FindIf(*, first, last, p)
        if (first == last) {
            None
        } else {
            Some(*(first))
        }
    }
}
