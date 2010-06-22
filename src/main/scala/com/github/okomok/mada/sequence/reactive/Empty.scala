

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package reactive


private[mada] case class Empty() extends Reactive[Nothing] {
    override def activate(k: Reactor[Nothing]) = {
        k.onEnd
    }
}
