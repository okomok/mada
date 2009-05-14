

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.any


@forwarder
trait Forwarder {
    protected def delegate: Any

    override def toString = delegate.toString
    override def hashCode = delegate.hashCode
}
