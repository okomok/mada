

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traverser


final class Memoize[A](val _1: Traverser[A]) extends Traverser[A] {
    private val e = new Proxies.Var[A]
    override def isEnd = _1.isEnd
    override def deref = {
        if (e.isNull) {
            e.assign(_1.deref)
        }
        e.self
    }
    override def increment = {
        _1.increment
        e.resign
    }
}
