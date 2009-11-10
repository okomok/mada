

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class DropWhile[A](_1: Reactive[A], _2: A => Boolean) extends Reactive[A] {
    override def subscribe(k: Reactor[A]) = {
        val j = new Reactor[A] {
            private var begins = false
            override def onEnd = k.onEnd
            override def react(e: A) = {
                if (!begins) {
                    if (_2(e)) {
                        begins = true
                        k.react(e)
                    }
                } else {
                    k.react(e)
                }
            }
        }
        _1.subscribe(beforeSubscribe(j))
    }
}
