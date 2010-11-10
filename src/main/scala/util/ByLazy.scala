

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package util


/**
 * Runs in the result-retrieving-site.
 */
case class ByLazy[+R](_1: Function0[R]) extends Function0[R] {
    private[this] lazy val v = _1()
    override def apply = v
}

object ByLazy extends EvaluationStrategy {
    override def install[R](to: Function0[R]): Function0[R] = new ByLazy(to)
    def apply[R](body: => R, o: Overload = ()): ByLazy[R] = new ByLazy(() => body)
    implicit def _fromExpr[R](from: => R): ByLazy[R] = apply(from)
}
