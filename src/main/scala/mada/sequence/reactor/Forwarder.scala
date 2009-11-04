

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactor



trait Forwarder[+A] extends Reactor[A] with util.Forwarder {
    override protected def delegate: Iterator[A]

    override def onEnd = delegate.onEnd
    override def react = delegate.react
    override def onError(x: Exception) = delegate.onError(x)
}
