

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterator


private
case class FromJIterator[A](_1: java.util.Iterator[A]) extends Forwarder[A] {
    override protected val delegate: Iterator[A] = _1 match {
        case ToJIterator(from) => from // from-to fusion
        case _ => new _FromJIterator(_1)
    }

    // to-from fusion is infeasible, because constructor has side-effects.
    // override def toJIterator = _1
}

private class _FromJIterator[A](_1: java.util.Iterator[A]) extends _Iterator[A] {
    private[this] var e = ready

    override protected def _isEnd = e.isEmpty
    override protected def _deref = e.get
    override protected def _increment = e = ready

    private def ready: Option[A] = if (_1.hasNext) Some(_1.next) else None
}


private
case class ToJIterator[A](_1: Iterator[A]) extends java.util.Iterator[A] {
    override def hasNext = !_1.isEnd
    override def next = {
        val tmp = ~_1
        _1.++
        tmp
    }
    override def remove = throw new UnsupportedOperationException("ToJIterator.remove")
}
