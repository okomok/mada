

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package reactive


private[mada] case class Catch[A](_1: Reactive[A], _2: Throwable => Unit) extends Reactive[A] {
    override def activate(k: Reactor[A]) = {
        _1.activate(reactor.make(_ => k.onEnd, e => try { k.react(e) } catch { case x => _2(x) }))
    }
}


/*

private[mada] case class Try[A](_1: Reactive[A]) {
    def `catch`(f: Throwable => Unit): TryCatch[A] = TryCatch(_1, f)
}

private[mada] case class TryCatch[A](_1: Reactive[A], _2: Throwable => Unit) extends Forwarder[A] {
    override protected val delegate: Reactive[A] = TryCatchFinally(_1, _2, util.byName(util.theUnit))
    def `finally`(g: => Unit): Reactive[A] = TryCatchFinally(_1, _2, util.byName(g))
}

private[mada] case class TryCatchFinally[A](_1: Reactive[A], _2: Throwable => Unit, _3: util.ByName[Unit]) extends Reactive[A] {
    override def activate(k: Reactor[A]) = {
        val j = new Reactor[A] {
            override def onEnd = k.onEnd
            override def react(e: A) = {
                try {
                    k.react(e)
                } catch {
                    case x => _2(x)
                } finally {
                    _3()
                }
            }
        }
        _1.activate(j)
    }
}

*/