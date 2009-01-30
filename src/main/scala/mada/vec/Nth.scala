

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Nth {
    def apply[A](v: Vector[A]): Vector[A] = new NthVector(v)

    def read[A](v: Vector[A], i: Int): A = v(v.start + i)
    def write[A](v: Vector[A], i: Int, e: A): Unit = v(v.start + i) = e
    def isDefinedAt[A](v: Vector[A], i: Int): Boolean = v.isDefinedAt(v.start + i)
}

private[mada] class NthVector[A](v: Vector[A]) extends Vector[A] {
    override def start = 0
    override def end = v.size

    override def apply(i: Int) = Nth.read(v, i)
    override def update(i: Int, e: A) = Nth.write(v, i, e)
    override def isDefinedAt(i: Int) = Nth.isDefinedAt(v, i)

    override def nth = this // nth-nth fusion
}
