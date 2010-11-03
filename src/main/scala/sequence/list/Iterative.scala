

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package list


private
case class AsIterative[A](_1: List[A]) extends Iterative[A] {
    override def begin = new _Iterator[A] {
        private[this] var it = _1
        override protected def _isEnd = it.isEmpty
        override protected def _deref = it.head
        override protected def _increment = it = it.tail
    }

    override def isEmpty = _1.isEmpty
}
