

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class Synchronize[+A](_1: Reactive[A]) extends Reactive[A] {
    override def subscribe(k: Reactor[A]) = {
        val j = k.synchronize
        _1.subscribe(j)
    }
}
