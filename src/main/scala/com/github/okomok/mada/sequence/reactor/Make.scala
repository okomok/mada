

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package reactor


case class Make[-A](_1: Unit => Unit, _2: A => Unit) extends Reactor[A] {
    override def onEnd = _1()
    override def react(e: A) = _2(e)
}
