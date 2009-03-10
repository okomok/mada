

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Cycle {
    def apply[A](v: Vector[A], n: Int): Vector[A] = new CycleVector(v, n)
}

private[mada] class CycleVector[A](v: Vector[A], n: Int) extends VectorProxy[A] {
    override val self = v.permutation{ i => Div.remainder(i, v.size) }.nth(0, v.size * n).readOnly
    override def cycle(_n: Int) = v.cycle(n * _n) // cycle-cycle fusion
}
