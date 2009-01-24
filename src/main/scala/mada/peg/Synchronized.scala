

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Synchronized {
    def apply[A](p: Peg[A]) = new SynchronizedPeg(p)
}

class SynchronizedPeg[A](p: Peg[A]) extends Peg[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = p.synchronized { p.parse(v, start, end) }
    override def length = p.synchronized { p.length }
}
