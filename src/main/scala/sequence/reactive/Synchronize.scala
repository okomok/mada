

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package reactive


// TODO

private[mada] case class Synchronize[+A](_1: Reactive[A]) extends Reactive[A] {
    override def activate(k: Reactor[A]) = {
        val j = k.synchronize
        _1.activate(j)
    }
}
