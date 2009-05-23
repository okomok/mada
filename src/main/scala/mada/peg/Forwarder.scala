

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


trait Forwarder[A] extends Peg[A] with util.Forwarder {
    override protected def delegate: Peg[A]

    final override def parse(v: Vector[A], start: Int, end: Int) = delegate.parse(v, start, end)
    final override def width = delegate.width

    // Should not be forwarded? every peg is a case class, expression.
    // override def toString: String = delegate.toString
}
