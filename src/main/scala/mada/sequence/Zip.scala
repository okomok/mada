

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


case class Zip[+A, +B](_1: Sequence[A], _2: Sequence[B]) extends Forwarder[(A, B)] {
    override protected val delegate = _1.zipBy(_2){ (a, b) => (a, b) }
}

case class ZipBy[A, B, +C](_1: Sequence[A], _2: Sequence[B], _3: (A, B) => C) extends Sequence[C] {
    override def begin = new Iterator[C] {
        private val it1 = _1.begin
        private val it2 = _2.begin

        override def isEnd = !it1 || !it2
        override def deref = { preDeref; _3(~it1, ~it2) }
        override def increment = { preIncrement; it1.++; it2.++ }
    }
}
