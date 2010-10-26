

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterative


/**
 * Helps to implement recursive infinite sequences.
 * This sequence is implicitly memoized.
 */
class Infinite[A] extends Iterative[A] {
    @volatile private[this] var f: Function0[Iterative[A]] = null

    /**
     * Assigns <code>that</code>.
     */
    def :=(that: => Iterative[A]): Unit = {
        f = util.ByLazy(that.memoize)
    }

    // init-memoize guarantees number of iterators never be exponential growth.
    override def begin = new Iterator[A] {
        private[this] var i = 0
        private[this] var it: Iterator[A] = null

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

    override def memoize: Iterative[A] = this // memoize-memoize fusion
}


private
case class Infinitize[A](_1: Iterative[A]) extends Iterative[Option[A]] {
    override def begin = new Iterator[Option[A]] {
        private[this] var i = 0
        private[this] var it: Iterator[A] = null

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
