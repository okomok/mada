

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package util


/**
 * Runs (possibly) in the thread-pool.
 * If the thread-pool is full, _2 determines the evaluation strategy.
 */
case class Parallel[+R](_1: Function0[R], _2: EvaluationStrategy) extends Function0[R] {
    private[this] val f = {
        try {
            Execute(_1)
        } catch {
            case _: java.util.concurrent.RejectedExecutionException => _2.install(_1)
        }
    }
    override def apply = f()
}

object Parallel {
    def apply[R](body: => R, stg: EvaluationStrategy, o: Overload = ()): Parallel[R] = new Parallel(() => body, stg)
}
