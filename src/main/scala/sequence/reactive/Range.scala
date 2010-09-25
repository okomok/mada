

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Range(_1: Int, _2: Int) extends GeneratorOnce[Int] {
    private var cur = _1

    override def generate = if (cur != _2) _next
    override def generateAll = while (cur != _2) _next

    private def _next: Unit = {
        out(cur)
        cur += 1
    }

    override val head = _1
}
