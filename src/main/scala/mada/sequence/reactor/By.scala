

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactor


case class By[-A](_1: A => Unit) extends Reactor[A] {
    override def onEnd = ()
    override def react(e: A) = _1(e)
}
