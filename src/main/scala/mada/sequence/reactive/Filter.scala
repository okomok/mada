

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class Filter[A](_1: Reactive[A], _2: A => Boolean) extends Reactive[A] {
    override def subscribe(t: Reactor[A]) = _1.subscribe(
        new Reactor[A] {
            override def onEnd = t.onEnd
            override def react(e: A) = if (p(e)) t.react(e) else ()
        }
    )
}
