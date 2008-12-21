

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Offset {
    def apply[A](v: Vector[A], i: Long, j: Long): Vector[A] = v.window(i, v.size + j)
}
