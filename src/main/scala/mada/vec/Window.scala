

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Window {
    def apply[A](v: Vector[A], n: Int, m: Int): Vector[A] = v.region(v.start + n, v.start + m)
}
