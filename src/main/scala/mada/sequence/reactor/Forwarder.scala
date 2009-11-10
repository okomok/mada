

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactor


// Probably unused.
trait Forwarder[-A] extends Reactor[A] with util.Forwarder {
    override protected def delegate: Reactor[A]

    override def onEnd = delegate.onEnd
    override def react(e: A) = delegate.react(e)
}
