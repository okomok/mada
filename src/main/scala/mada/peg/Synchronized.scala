

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Synchronized {
    def apply[A](p: Peg[A]): Peg[A] = new SynchronizedPeg(p)
}

private[mada] class SynchronizedPeg[A](override val self: Peg[A]) extends PegProxy[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = synchronized { self.parse(v, start, end) }
    override def width = synchronized { self.width }
}
