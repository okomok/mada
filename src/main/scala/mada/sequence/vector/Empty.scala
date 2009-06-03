

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


private[mada] object Empty {
    def apply[A]: Vector[A] = impl.asInstanceOf[Vector[A]]
    private val impl: Vector[Any] = new EmptyVector[Any]
}

private[mada] class EmptyVector[A] extends Vector[A] {
    override def start = 0
    override def end = 0
}
