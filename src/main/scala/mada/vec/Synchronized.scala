

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Synchronized {
    def apply[A](v: Vector[A]) = new SynchronizedVector(v)
}

class SynchronizedVector[A](override val self: Vector[A]) extends VectorProxy[A] {
    override def size: Int = self.synchronized { self.size }
    override def apply(i: Int): A = self.synchronized { self.apply(i) }
    override def update(i: Int, e: A): Unit = self.synchronized { self.update(i, e) }
}
