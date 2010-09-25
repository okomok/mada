

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class UnfoldRight[A, B](_1: A, _2: A => Option[(B, A)], _3: util.ByName[Unit] = util.byName(())) extends GeneratorOnce[B] {
    private var acc = _2(_1)
    private var onEnd = _3

    override def generateOne = if (!acc.isEmpty) _next
    override def generateAll = while (!acc.isEmpty) _next

    private def _next: Unit = {
        out(acc.get._1)
        acc = _2(acc.get._2)
        if (acc.isEmpty) onEnd()
    }

    override def endWith(f: => Unit): Reactive[B] = {
        val old = onEnd.copy()
        onEnd = util.byName{old();f}
        this
    }
}
