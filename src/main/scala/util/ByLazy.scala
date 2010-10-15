

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package util


case class ByLazy[+R](_1: Function0[R]) extends Function0[R] {
    private lazy val v = _1()
    override def apply = v
}

object ByLazy {
    def apply[R](body: => R, dummy: Int = 0): ByLazy[R] = new ByLazy(() => body)
}
