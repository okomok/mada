

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


@notThreadSafe
case class Unique[+A](_1: Reactive[A]) extends Forwarder[A] {
    override protected val delegate = _1.uniqueBy(function.equal)

    override def unique: Reactive[A] = this // unique-unique fusion
}

@notThreadSafe
case class UniqueBy[A](_1: Reactive[A], _2: (A, A) => Boolean) extends Reactive[A] {
    override def subscribe(k: Reactor[A]) = {
        val j = new Reactor[A] {
            private var isHead = true
            private var u: A = _
            override def onEnd = k.onEnd
            override def react(e: A): Unit = {
                if (isHead) {
                    isHead = false
                    u = e
                } else {
                    if (_2(u, e)) {
                        return
                    } else {
                        u = e
                    }
                }
                k.react(u)
            }
        }
        _1.subscribe(j)
    }
}
