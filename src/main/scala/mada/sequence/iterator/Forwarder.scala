

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package iterator


trait Forwarder[+A] extends Iterator[A] with util.Forwarder {
    override protected def delegate: Iterator[A]

    override def isEnd = delegate.isEnd
    override def deref = delegate.deref
    override def increment = delegate.increment
}
