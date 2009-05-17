

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


case class Zip[+A, +B](_1: Sequence[A], _2: Sequence[B]) extends Sequence[(A, B)] {
    override def begin = new Iterator[(A, B)] {
        private val it1 = _1.begin
        private val it2 = _2.begin

        override def isEnd = !it1 || !it2
        override def deref = { preDeref; (~it1, ~it2) }
        override def increment = { preIncrement; it1.++; it2.++ }
    }
}
