

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class Single[+A](_1: A) extends Reactive[A] {
    override def start(k: Reactor[A]) = {
        k.react(_1)
        k.onEnd
    }
}
