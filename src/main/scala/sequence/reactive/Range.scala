

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Range(_1: Int, _2: Int) extends Generator.Trivial[Int] {
    private var cur = _1
    private def _next(f: Int => Unit) = { f(cur); cur += 1 }

    override protected def generateTo(f: Int => Unit) = if (cur != _2) _next(f)
    override protected def generateAllTo(fs: Iterative[Int => Unit]) = {
        for (f <- fs) {
            while (cur != _2) _next(f)
        }
    }
}
