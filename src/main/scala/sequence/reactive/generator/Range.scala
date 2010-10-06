

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive; package generator


private
case class Range(_1: Int, _2: Int) extends TrivialGenerator[Int] {
    private var cur = _1

    private def _next(f: Int => Unit) {
        val now = cur
        cur += 1
        f(now)
    }

    override def isEnd = cur == _2
    override protected def generateTo(f: Int => Unit) = if (!isEnd) _next(f)
    override protected def generateAllTo(fs: Iterative[Int => Unit]) = {
        for (f <- fs) {
            while (!isEnd) _next(f)
        }
    }
}
