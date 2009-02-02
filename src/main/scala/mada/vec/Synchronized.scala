

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object Synchronized {
    def apply[A](v: Vector[A]) = new SynchronizedVector(v)
}

private[mada] class SynchronizedVector[A](override val underlying: Vector[A]) extends Adapter.Transform[A] {
    override def start = underlying.synchronized { underlying.start }
    override def end = underlying.synchronized { underlying.end }
    override def apply(i: Int): A = underlying.synchronized { underlying.apply(start + i) }
    override def update(i: Int, e: A): Unit = underlying.synchronized { underlying.update(start + i, e) }
    override def isDefinedAt(i: Int): Boolean = underlying.synchronized { underlying.isDefinedAt(i) }
}
