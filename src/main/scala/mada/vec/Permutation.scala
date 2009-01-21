

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Permutation {
    def apply[A](ev: Vector[A], iv: Vector[Int]): Vector[A] = new PermutationVector(iv, ev)
}

class PermutationVector[A](override val * : Vector[Int], ev: Vector[A]) extends VectorAdapter[Int, A] {
    override def apply(i: Int) = ev(*(i))
    override def update(i: Int, e: A) = ev(*(i)) = e
}
