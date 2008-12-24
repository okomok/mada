

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Partition {
    def apply[A](v: Vector[A], p: A => Boolean): (Vector[A], Vector[A]) = (v.filter(p), v.remove(p))
}