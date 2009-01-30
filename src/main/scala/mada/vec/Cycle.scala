

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Cycle {
    def apply[A](v: Vector[A], n: Int): Vector[A] = new CycleVector(v, n)
}

private[mada] class CycleVector[A](v: Vector[A], n: Int) extends Vector[A] {
    override def start = 0
    override def end = v.size * n

    override def apply(i: Int) = v.nth(Div.remainder(i, v.size))
    // isDefinedAt is restrictive because v.size affects.

    override def cycle(_n: Int) = v.cycle(n * _n) // cycle-cycle fusion
}
