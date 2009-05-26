

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


/**
 * Helps to implement recursive(infinite) sequences.
 *
 * @pre A recursive sequence expression shall not contain side-effects.
 */
class Rec[A] extends Sequence[A] {
    private var f: Function0[Sequence[A]] = null

    /**
     * Assigns <code>that</code>.
     */
    def :=(that: => Sequence[A]): Unit = {
        f = function.ofLazy(that.memoize)
    }

    // memoize and init guarantees method invocation to be constant amortized time;
    // otherwise, any trivial expression may result in an exponential series of "begin".
    override def begin = new Iterator[A] {
        private var i = 0
        private var t: Iterator[A] = null

        override def isEnd = false
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
                t = f().begin // begin too is "memoized" by memoized deref.
                t.advance(i)
            }
        }
    }
/*
    override def begin = new iterator.Forwarder[A] {
        override protected lazy val delegate = f().begin
    }
*/
}
