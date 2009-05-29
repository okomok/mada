

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


/**
 * Helps to implement recursive sequences.
 */
class Recursive[A] extends Sequence[A] {
    @volatile private var f: Function0[Sequence[A]] = null

    /**
     * Assigns <code>that</code>.
     */
    def :=(that: => Sequence[A]): Unit = {
        f = function.ofLazy(that)
    }

    override def begin: Iterator[A] = f().begin
}
