

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive; package generator


private
class FromSIterable[A](_1: Iterable[A]) extends TrivialGenerator[A] {
    private val it = _1.iterator
    private def _next(f: A => Unit) = f(it.next)

    override def isEmpty = synchronized { it.isEmpty }
    override protected def generateTo(f: A => Unit) = synchronized { if (!isEmpty) _next(f) }
    override protected def generateAllTo(fs: Iterative[A => Unit]) = {
        for (f <- fs) {
            synchronized { while (!isEmpty) _next(f) }
        }
    }
}
