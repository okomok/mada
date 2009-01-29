

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Rotate {
    def apply[A](v: Vector[A], i: Int): Vector[A] = v(v.start + i, v.end) ++ v(v.start, v.start + i)
}
