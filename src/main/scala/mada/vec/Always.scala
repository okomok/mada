

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Always {
    def apply[A, B](v: Vector[A], w: Vector[B]): Vector[B] = w
}
