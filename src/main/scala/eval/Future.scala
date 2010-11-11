

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package eval


/**
 * Runs (possibly) in the thread-pool.
 * If the thread-pool is full, _1 runs in the result-retrieving-site.
 */
case class Future[+R](_1: Function0[R]) extends Function0[R] {
    private[this] val f = Parallel(_1, ByLazy)
    override def apply = f()
}

object Future {
    def apply[R](body: => R, o: AsFunction = ()): Future[R] = new Future(() => body)
}
