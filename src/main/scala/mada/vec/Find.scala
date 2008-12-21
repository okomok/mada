

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Find {
    def apply[A](* : Vector[A], p: A => Boolean): Option[A] = {
        var (__first, __last) = (*.stlFindIf(p), *.size)

        if (__first == __last) {
            None
        } else {
            Some(*(__first))
        }
    }
}
