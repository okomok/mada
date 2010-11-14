

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterative


private
case class UnfoldRight[A, +B](_1: A, _2: A => Option[(B, A)]) extends Iterative[B] {
    override def begin = new _Iterator[B] {
        private[this] var acc = _2(_1)

        override protected def _isEnd = acc.isEmpty
        override protected def _deref = acc.get._1
        override protected def _increment() = acc = _2(acc.get._2)
    }
}
