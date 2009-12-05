

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class Empty() extends Reactive[Nothing] {
    override def subscribe(k: Reactor[Nothing]) = {
        k.onEnd
    }
}
