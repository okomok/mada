

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traverser


class FromIterator[A](val that: Iterator[A]) extends Forwarder[A] {
    override val underlying: Traverser[A] = that match {
        case that: ToIterator[_] => that.that // from-to fusion
        case _ => new _FromIterator(that)
    }
}

private[mada] class _FromIterator[A](that: Iterator[A]) extends Traverser[A] {
    private val e = new Proxies.Var[A]
    if (that.hasNext) {
        e.assign(that.next)
    }

    override def isEnd = e.isNull
    override def deref = e.self
    override def increment = {
        if (that.hasNext) {
            e.assign(that.next)
        } else {
            e.resign
        }

    }

    // to-from fusion is infeasible, because constructor has side-effects.
    // override def toIterator = that
}


class ToIterator[A](val that: Traverser[A]) extends Iterator[A] {
    override def hasNext = !that.isEnd
    override def next = {
        val tmp = ~that
        that.++
        tmp
    }
}
