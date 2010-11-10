

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package util


/**
 * Runs in a newly created thread.
 */
case class ByThread[R](_1: Function0[R]) extends Function0[R] {
    private[this] val f = Invoke(_1, r => new Thread(r).start)
    override def apply = f()
}

object ByThread extends EvaluationStrategy {
    override def install[R](to: Function0[R]): Function0[R] = new ByThread(to)
    def apply[R](body: => R, o: Overload = ()): ByThread[R] = new ByThread(() => body)
}
