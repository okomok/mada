

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Permutation {
    def apply[A](es: Vector[A], is: Vector[Long]): Vector[A] = new PermutationVector(is, es)
}

class PermutationVector[A](override val * : Vector[Long], es: Vector[A]) extends Adapter[Long, A] {
    override def apply(i: Long) = es(*(i))
    override def update(i: Long, e: A) = es(*(i)) = e
}
