

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package reactive


// Parallel-safe reaction seems meaningless: fold is essentially sequencial.


@notThreadSafe
private[mada] case class FolderLeft[A, B](_1: Reactive[A], _2: B, _3: (B, A) => B) extends Reactive[B] {
    override def activate(k: Reactor[B]) = {
        val j = new Reactor[A] {
            private var isHead = true
            private var z = _2
            override def onEnd = k.onEnd
            override def react(e: A) = {
                if (isHead) {
                    isHead = false
                    k.react(_2)
                }
                z = _3(z, e)
                k.react(z)
            }
        }
        _1.activate(j)
    }
}


@notThreadSafe
private[mada] case class ReducerLeft[A, B >: A](_1: Reactive[A], _2: (B, A) => B) extends Reactive[B] {
    override def activate(k: Reactor[B]) = {
        val j = new Reactor[A] {
            private var isHead = true
            private var z: B = _
            override def onEnd = k.onEnd
            override def react(e: A) = {
                if (isHead) {
                    isHead = false
                    z = e
                } else {
                    z = _2(z, e)
                }
                k.react(z)
            }
        }
        _1.activate(j)
    }
}
