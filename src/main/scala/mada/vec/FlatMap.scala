

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object FlatMap {
    def apply[A, B](v: Vector[A], f: A => Vector[B]): Vector[B] = Vector.flatten(v.map(f))
}
