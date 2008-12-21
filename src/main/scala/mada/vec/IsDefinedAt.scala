

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object IsDefinedAt {
    def apply[A](v: Vector[A], x: Long): Boolean = (x >= 0) && (x < v.size)
}
