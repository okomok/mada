

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


/**
 * Helps to implement recursive infinite sequences.
 * This sequence is implicitly memoized.
 */
class Infinite[A] extends Sequence[A] {
    @volatile private var f: Function0[Sequence[A]] = null

    /**
     * Assigns <code>that</code>.
     */
    def :=(that: => Sequence[A]): Unit = {
        f = function.ofLazy(that.memoize)
    }

    // init-memoize guarantees number of iterators never be exponential growth.
    override def begin = new Iterator[A] {
        private var i = 0
        private var it: Iterator[A] = null

        override def isEnd = false // infinite
        override def deref = { init; ~it }
        override def increment = {
            if (it eq null) {
                i += 1
            } else {
                it.++
            }
        }

        private def init: Unit = {
            if (it eq null) {
                it = f().begin // wrapped around memoized deref.
                it.advance(i)
            }
        }
    }

    override def memoize: Sequence[A] = this // memoize-memoize fusion
}


case class Infinitize[A](_1: Sequence[A]) extends Sequence[Option[A]] {
    override def begin = new Iterator[Option[A]] {
        private var i = 0
        private var it: Iterator[A] = null

        override def isEnd = false
        override def deref = {
            init
            if (it) Some(~it) else None
        }
        override def increment = {
            if (it eq null) {
                i += 1
            } else if (it) {
                it.++
            }
        }

        private def init: Unit = {
            if (it eq null) {
                it = _1.begin
                it.advance(i)
            }
        }
    }
}
