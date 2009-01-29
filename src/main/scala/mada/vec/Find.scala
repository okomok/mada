

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Find {
    def apply[A](v: Vector[A], p: A => Boolean): Option[A] = {
        val i = stl.FindIf(v, v.start, v.end, p)
        if (i == v.end) {
            None
        } else {
            Some(v(i))
        }
    }
}
