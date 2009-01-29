

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object IsDefinedAt {
    def apply[A](v: Vector[A], x: Int): Boolean = (x >= v.start) && (x < v.end)
}
