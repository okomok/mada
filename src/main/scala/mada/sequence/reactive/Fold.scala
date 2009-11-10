

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class FolderLeft[A, B](_1: Reactive[A], _2: B, _3: (B, A) => B) extends Reactive[B] {
    override def subscribe(k: Reactor[B]) = {
        val j = new Reactor[A] {
            private var z = _2
            override def onEnd = {
                k.react(z) // delayed reaction may be a problem?
                k.onEnd
            }
            override def react(e: A) = {
                k.react(z)
                z = _3(z, e)
            }
        }
        _1.subscribe(_1.beforeSubscribe(j))
    }
}


case class ReducerLeft[A, B >: A](_1: Reactive[A], _2: (B, A) => B) extends Reactive[B] {
    override def subscribe(k: Reactor[B]) = {
        val j = new Reactor[A] {
            private var z: B = _
            private var isHead = true
            override def onEnd = k.onEnd
            override def react(e: A) = {
                if (isHead) {
                    k.react(e)
                    z = e
                    isHead = false
                } else {
                    z = _2(z, e)
                    k.react(z)
                }
            }
        }
        _1.subscribe(_1.beforeSubscribe(j))
    }
}
