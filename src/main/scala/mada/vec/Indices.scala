

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Indices {
    def apply[A](v: Vector[A]): Vector[Int] = Vector.range(0, v.size)
}
