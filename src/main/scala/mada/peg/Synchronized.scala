

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Synchronized {
    def apply[A](p: Peg[A]) = new SynchronizedPeg(p)
}

class SynchronizedPeg[A](override val self: Peg[A]) extends PegProxy[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        self.synchronized { self.parse(v, start, end) }
    }
}
