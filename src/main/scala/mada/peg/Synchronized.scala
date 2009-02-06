

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object Synchronized {
    def apply[A](p: Peg[A]) = new SynchronizedPeg(p)
}

private[mada] class SynchronizedPeg[A](p: Peg[A]) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = p.synchronized { p.parse(v, start, end) }
    override def width = p.synchronized { p.width }
}
