

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package iterator


private
case class FromSIterator[A](_1: scala.Iterator[A]) extends Forwarder[A] {
    override protected val delegate: Iterator[A] = _1 match {
        case ToSIterator(from) => from // from-to fusion
        case _ => new _FromSIterator(_1)
    }

    // to-from fusion is infeasible, because constructor has side-effects.
    // override def toSIterator = _1
}

private class _FromSIterator[A](_1: scala.Iterator[A]) extends Iterator[A] {
    private var e = ready

    override def isEnd = e.isEmpty
    override def deref = { preDeref; e.get }
    override def increment = { preIncrement; e = ready }

    private def ready: Option[A] = if (_1.hasNext) Some(_1.next) else None
}


private
case class ToSIterator[A](_1: Iterator[A]) extends scala.Iterator[A] {
    override def hasNext = !_1.isEnd
    override def next = {
        val tmp = ~_1
        _1.++
        tmp
    }
}
