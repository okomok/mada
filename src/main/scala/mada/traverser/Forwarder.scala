

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traverser


// probably unused.
trait Forwarder[+A] extends Traverser[A] with any.Forwarder {
    override protected def delegate: Traverser[A]

    override def isEnd = delegate.isEnd
    override def deref = delegate.deref
    override def increment = delegate.increment
}
