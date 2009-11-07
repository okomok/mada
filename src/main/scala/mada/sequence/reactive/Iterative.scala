

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class FromIterative[+A](_1: iterative.Sequence[A]) extends Reactive[A] {
    override def subscribe(k: Reactor[A]) = {
        _1.asIterative.foreach{ e => k.react(e) }
        k.onEnd
    }
}
