

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


import java.util.LinkedList


case class Zip[+A, +B](_1: Reactive[A], _2: Reactive[B]) extends Forwarder[(A, B)] {
    override protected val delegate = _1.zipBy(_2){ (a, b) => (a, b) }
}

case class ZipBy[A, B, +C](_1: Reactive[A], _2: Reactive[B], _3: (A, B) => C) extends Reactive[C] {
    override def subscribe(k: Reactor[C]) = {
        val k_onEnd = new OnlyFirst[Unit](_ => k.onEnd)
        var ends1 = false
        var ends2 = false
        val q1 = new LinkedList[A]
        val q2 = new LinkedList[B]
        def invariant = util.assert(q1.isEmpty || q2.isEmpty)

        val j1 = new Reactor[A] {
            override def onEnd = {
                invariant
                ends1 = true
                if (ends2) {
                    k_onEnd()
                }
            }
            override def react(e: A): Unit = {
                invariant
                if (k_onEnd.isDone) {
                    return
                }
                if (q2.isEmpty) {
                    q1.add(e)
                } else {
                    k.react(_3(e, q2.poll))
                    if (ends2 && q2.isEmpty) {
                        k_onEnd()
                    }
                }
            }
        }
        val j2 = new Reactor[B] {
            override def onEnd = {
                invariant
                ends2 = true
                if (ends1) {
                    k_onEnd()
                }
            }
            override def react(e: B): Unit = {
                invariant
                if (k_onEnd.isDone) {
                    return
                }
                if (q1.isEmpty) {
                    q2.add(e)
                } else {
                    k.react(_3(q1.poll, e))
                    if (ends1 && q1.isEmpty) {
                        k_onEnd()
                    }
                }
            }
        }

        _1.subscribe(j1)
        _2.subscribe(j2)
    }
}
