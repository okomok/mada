

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive; package generator


private
case class UnfoldRight[A, B](_1: A, _2: A => Option[(B, A)]) extends TrivialGenerator[B] {
    private var acc = _2(_1)
    private def _next(f: B => Unit) {
        val now = acc.get._1
        acc = _2(acc.get._2)
        f(now)
    }

    override def isEnd = synchronized { acc.isEmpty }
    override protected def generateTo(f: B => Unit) = synchronized { if (!isEnd) _next(f) }
    override protected def generateAllTo(fs: Iterative[B => Unit]) = {
        for (f <- fs) {
            synchronized { while (!isEnd) _next(f) }
        }
    }
}
