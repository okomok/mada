

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Permutation {
    def apply[A](ev: Vector[A], iv: Vector[Long]): Vector[A] = new PermutationVector(iv, ev)
}

class PermutationVector[A](override val * : Vector[Long], ev: Vector[A]) extends VectorAdapter[Long, A] {
    override def apply(i: Long) = ev(*(i))
    override def update(i: Long, e: A) = ev(*(i)) = e
}
