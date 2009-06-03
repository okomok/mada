

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


trait Forwarder[+A] extends Sequence[A] with util.Forwarder {
    override protected def delegate: Sequence[A]

    override def toIterative = delegate.toIterative
    override def equals(that: Any): Boolean = delegate.equals(that)
    override def hashCode: Int = delegate.hashCode
    override def toString: String = delegate.toString
}
