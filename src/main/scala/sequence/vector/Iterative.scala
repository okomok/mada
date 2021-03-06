

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


private
case class AsIterative[A](_1: Vector[A]) extends Iterative[A] {
    override def begin = new _Iterator[A] {
        private[this] var i = _1.start
        override protected def _isEnd = i == _1.end
        override protected def _deref = _1(i)
        override protected def _increment() = i += 1
    }

    // TODO: override optimizable methods.
    override def isEmpty = _1.isEmpty
}
