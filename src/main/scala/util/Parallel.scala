

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package util


/**
 * Runs (possibly) in the thread-pool.
 * If the thread-pool is full, _1 runs in the constructor-call-site.
 */
case class Parallel[+R](_1: Function0[R]) extends Function0[R] {
    private[this] val f = {
        try {
            Execute(_1)
        } catch {
            case _: java.util.concurrent.RejectedExecutionException => Strict(_1)
        }
    }
    override def apply = f()
}

object Parallel {
    def apply[R](body: => R, o: Overload = ()): Parallel[R] = new Parallel(() => body)
}
