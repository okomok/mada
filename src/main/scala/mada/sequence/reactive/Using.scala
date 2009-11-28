

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


@notThreadSafe
case class Using[+A](_1: Reactive[A], _2: util.ByLazy[Auto[Any]]) extends Reactive[A] {
    override def start(k: Reactor[A]) = {
        val j = new Reactor[A] {
            private val _autoBegin = util.byLazy(_2().begin)
            private val _autoEnd = new OnlyFirst[Unit](_ => _2().end)
            override def onEnd = {
                if (!_autoEnd.isDone) {
                    k.onEnd
                    _autoEnd()
                }
            }
            override def react(e: A) = {
                _autoBegin()
                if (!_autoEnd.isDone) {
                    try {
                        k.react(e)
                    } catch {
                        case x => _autoEnd(); throw x
                    }
                }
            }
        }
        _1.start(j)
    }
}
