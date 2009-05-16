

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class Unique[A](_1: Traversable[A]) extends Forwarder[A] {
    override protected val delegate = _1.uniqueBy(function.equal)
}

case class UniqueBy[A](_1: Traversable[A], _2: (A, A) => Boolean) extends Traversable[A] {
    override def start = new Traverser[A] {
        private val t = _1.start

        override def isEnd = !t
        override def deref = ~t
        override def increment = {
            val tmp = ~t
            t.++
            t.advanceWhile{ e => _2(tmp, e) }
        }
    }

    override def unique = _1.unique // unique-unique fusion
}
