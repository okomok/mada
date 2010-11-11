

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package eval


/**
 * Runs in the result-retrieving-site.
 */
case class ByLazy[+R](_1: Function0[R]) extends Function0[R] {
    private[this] lazy val v = _1()
    override def apply = v
}

object ByLazy extends Strategy {
    override def apply[R](body: => R, o: util.Overload = ()) = new ByLazy(() => body)
    implicit def _fromExpr[R](from: => R): ByLazy[R] = apply(from)
}
