

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traverser


case class FromJclIterator[A](_1: java.util.Iterator[A]) extends Forwarder[A] {
    override protected val delegate: Traverser[A] = _1 match {
        case ToJclIterator(from) => from // from-to fusion
        case _ => new _FromJclIterator(_1)
    }

    // to-from fusion is infeasible, because constructor has side-effects.
    // override def toIterator = _1
}

private class _FromJclIterator[A](_1: java.util.Iterator[A]) extends Traverser[A] {
    private var e = ready

    override def isEnd = e.isEmpty
    override def deref = { preDeref; e.get }
    override def increment = { preIncrement; e = ready }

    private def ready: Option[A] = if (_1.hasNext) Some(_1.next) else None
}


case class ToJclIterator[A](_1: Traverser[A]) extends java.util.Iterator[A] {
    override def hasNext = !_1.isEnd
    override def next = {
        val tmp = ~_1
        _1.++
        tmp
    }
    override def remove = throw new UnsupportedOperationException("ToJclIterator.remove")
}
