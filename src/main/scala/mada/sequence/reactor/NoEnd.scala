

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactor


case class NoEnd[-A](_1: Reactor[A]) extends Forwarder[A] {
    override protected val delegate = _1
    override def onEnd = ()
}
