

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package eval


/**
 * Runs in a newly created thread.
 */
case class ByThread[R](_1: Function0[R]) extends Function0[R] {
    private[this] val f = Invoke(_1, r => new Thread(r).start)
    override def apply = f()
}

object ByThread extends Strategy {
    def apply[R](body: => R, o: util.Overload = ()): ByThread[R] = new ByThread(() => body)
}
