

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class Try[A](_1: Reactive[A]) {
    def `catch`(f: Throwable => Unit): TryCatch[A] = TryCatch(_1, f)
}

case class TryCatch[A](_1: Reactive[A], _2: Throwable => Unit) extends Forwarder[A] {
    override protected val delegate: Reactive[A] = TryCatchFinally(_1, _2, util.byName(util.theUnit))
    def `finally`(g: => Unit): Reactive[A] = TryCatchFinally(_1, _2, util.byName(g))
}

case class TryCatchFinally[A](_1: Reactive[A], _2: Throwable => Unit, _3: util.ByName[Unit]) extends Reactive[A] {
    override def subscribe(k: Reactor[A]) = _1.subscribe(k)

    override def beforeSubscribe[B](k: Reactor[B]) = new Reactor[B] {
        override def onEnd = k.onEnd
        override def react(e: B) = {
            try {
                k.react(e)
            } catch {
                case x => _2(x)
            } finally {
                _3()
            }
        }
    }
}
