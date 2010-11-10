

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package util


/**
 * Runs in constructor-call-site.
 */
case class Strict[+R](_1: Function0[R]) extends Function0[R] {
    private[this] val v = _1()
    override def apply = v
}

object Strict extends EvaluationStrategy {
    override def install[R](to: Function0[R]): Function0[R] = new Strict(to)
    def apply[R](body: => R, o: Overload = ()): Strict[R] = new Strict(() => body)
}
