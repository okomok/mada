

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Find {
    def apply[A](* : Vector[A], p: A => Boolean): Option[A] = {
        var (x, first, last) = *.triple

        first = stl.FindIf(x, first, last, p)
        if (first == last) {
            None
        } else {
            Some(x(first))
        }
    }
}
