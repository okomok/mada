

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


case class Unique[+A](_1: Sequence[A]) extends Forwarder[A] {
    override protected val delegate = _1.uniqueBy(function.equal)
}

case class UniqueBy[A](_1: Sequence[A], _2: (A, A) => Boolean) extends Sequence[A] {
    override def begin = new Iterator[A] {
        private val it = _1.begin

        override def isEnd = !it
        override def deref = ~it
        override def increment = {
            val tmp = ~it
            it.++
            it.advanceWhile{ e => _2(tmp, e) }
        }
    }

    override def unique = _1.unique // unique-unique fusion
}
