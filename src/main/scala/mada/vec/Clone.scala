

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Clone {
    def apply[A](v: Vector[A]): Vector[A] = Vector.arrayVector(v.toArray)
}
