

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traverser


// probably unused.
@forwarder
trait Forwarder[+A] extends Traverser[A] {
    protected def underlying: Traverser[A]
    override def isEnd = underlying.isEnd
    override def deref = underlying.deref
    override def increment = underlying.increment
}
