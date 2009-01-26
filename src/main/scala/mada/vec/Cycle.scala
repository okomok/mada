

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Cycle {
    def apply[A](v: Vector[A], n: Int): Vector[A] = new CycleVector(v, n)
}

private[mada] class CycleVector[A](v: Vector[A], n: Int) extends Vector[A] {
    override def size = v.size * n
    override def apply(i: Int) = v(Div.remainder(i, v.size))

    override def cycle(_n: Int) = v.cycle(n * _n)
}
