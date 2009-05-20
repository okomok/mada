

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterator


case class Memoize[A](_1: Iterator[A]) extends Iterator[A] {
    private var e = option.NoneOf[A]

    override def isEnd = _1.isEnd
    override def deref = {
        if (e.isEmpty) {
            e = Some(_1.deref)
        }
        e.get
    }
    override def increment = {
        _1.increment
        e = None
    }
}
