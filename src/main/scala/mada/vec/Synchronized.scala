

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Synchronized {
    def apply[A](v: Vector[A]) = new SynchronizedVector(v)
}

private[mada] class SynchronizedVector[A](override val * : Vector[A]) extends VectorAdapter[A, A] {
    override def size: Int = *.synchronized { *.size }
    override def apply(i: Int): A = *.synchronized { *.apply(i) }
    override def update(i: Int, e: A): Unit = *.synchronized { *.update(i, e) }
}
