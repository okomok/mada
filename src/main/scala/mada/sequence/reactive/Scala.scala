

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class FromArray[A](_1: Array[A]) extends Forwarder[A] {
    override protected val delegate = fromSIterable(_1)
}

case class FromSIterable[+A](_1: Iterable[A]) extends Reactive[A] {
    override def activate(k: Reactor[A]) = {
        _1.foreach{ e => k.react(e) }
        k.onEnd
    }
}
