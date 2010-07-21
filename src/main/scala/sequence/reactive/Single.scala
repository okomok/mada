

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package reactive


private[mada] case class Single[+A](_1: A) extends Reactive[A] {
    override def activate(k: Reactor[A]) = {
        k.react(_1)
        k.onEnd
    }
}
