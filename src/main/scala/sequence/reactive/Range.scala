

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Range(_1: Int, _2: Int, _3: util.ByName[Unit] = util.byName(())) extends GeneratorOnce[Int] {
    private var cur = _1
    private var onEnd = _3

    override def generateOne = if (cur != _2) _next
    override def generateAll = while (cur != _2) _next

    private def _next = {
        out(cur)
        cur += 1
        if (cur == _2) {
            onEnd()
        }
    }

    override def endWith(f: => Unit): Reactive[Int] = {
        val old = onEnd.copy()
        onEnd = util.byName{old();f}
        this
    }

    override val head = _1
}
