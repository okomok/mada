

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Cycle {
    def apply[A](v: Vector[A], n: Long): Vector[A] = new CycleVector(v, n)
}

class CycleVector[A](v: Vector[A], n: Long) extends Vector[A] {
    override def size = v.size * n
    override def apply(i: Long) = v(positiveRemainder(i, v.size))

    override def cycle(_n: Long) = v.cycle(n * _n)

    private def positiveRemainder(a: Long, b: Long): Long = {
        Assert(b >= 0)
        val rem = a % b
        if (rem < 0) {
            rem + b
        } else {
            rem
        }
    }
}
