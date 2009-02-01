

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Permutation {
    def apply[A](v: Vector[A], f: Int => Int): Vector[A] = new PermutationVector(v, f)
}

private[mada] class PermutationVector[A](v: Vector[A], f: Int => Int) extends Adapter[A, A] {
    override val underlying = v.nth

    override def apply(i: Int) = underlying(f(i))
    override def update(i: Int, e: A) = underlying(f(i)) = e
    override def isDefinedAt(i: Int) = underlying.isDefinedAt(f(i))
}
