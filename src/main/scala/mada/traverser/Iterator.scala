

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traverser


final class FromIterator[A](val _1: Iterator[A]) extends Forwarder[A] {
    override val underlying: Traverser[A] = _1 match {
        case _1: ToIterator[_] => _1._1 // from-to fusion
        case _ => new _FromIterator(_1)
    }
}

private[mada] final class _FromIterator[A](_1: Iterator[A]) extends Traverser[A] {
    private val e = new Proxies.Var[A]
    if (_1.hasNext) {
        e.assign(_1.next)
    }

    override def isEnd = e.isNull
    override def deref = e.self
    override def increment = {
        if (_1.hasNext) {
            e.assign(_1.next)
        } else {
            e.resign
        }

    }

    // to-from fusion is infeasible, because constructor has side-effects.
    // override def toIterator = _1
}


final class ToIterator[A](val _1: Traverser[A]) extends Iterator[A] {
    override def hasNext = !_1.isEnd
    override def next = {
        val tmp = ~_1
        _1.++
        tmp
    }
}
