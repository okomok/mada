

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object IsEmpty {
    def apply[A](v: Vector[A]): Boolean = v.size == 0
}
