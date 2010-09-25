

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class UnfoldRight[A, B](_1: A, _2: A => Option[(B, A)]) extends GeneratorOnce[B] {
    private var acc = _2(_1)

    override def generate = if (!acc.isEmpty) _next
    override def generateAll = while (!acc.isEmpty) _next

    private def _next: Unit = {
        out(acc.get._1)
        acc = _2(acc.get._2)
    }
}
