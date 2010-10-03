

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class UnfoldRight[A, B](_1: A, _2: A => Option[(B, A)]) extends Generator.Trivial[B] {
    private var acc = _2(_1)
    private def _next(f: B => Unit) { f(acc.get._1); acc = _2(acc.get._2) }

    override protected def generateTo(f: B => Unit) = synchronized { if (!acc.isEmpty) _next(f) }
    override protected def generateAllTo(fs: Iterative[B => Unit]) = {
        for (f <- fs) {
            synchronized { while (!acc.isEmpty) _next(f) }
        }
    }
}
