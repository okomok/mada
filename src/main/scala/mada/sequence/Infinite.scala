

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
        private var t: Iterator[A] = null

        override def isEnd = false // infinite
        override def deref = { init; ~t }
        override def increment = {
            if (t eq null) {
                i += 1
            } else {
                t.++
            }
        }

        private def init: Unit = {
            if (t eq null) {
                t = f().begin // begin too is "memoized".
                t.advance(i)
            }
        }
    }

    override def memoize: Sequence[A] = this // memoize-memoize fusion
}
