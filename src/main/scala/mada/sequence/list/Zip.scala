

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.list


case class Zip[+A, +B](_1: List[A], _2: function.OfLazy[List[B]]) extends Forwarder[(A, B)] {
    override protected val delegate = _1.zipBy(_2()){ (a, b) => (a, b) }
}

case class ZipBy[A, B, +C](_1: List[A], _2: function.OfLazy[List[B]], _3: (A, B) => C) extends List[C] {
    override lazy val isEmpty = !_1.isEmpty || !_2().isEmpty
    override lazy val head = { preHead; _3(_1.head, _2().head) }
    override lazy val tail = { preTail; _1.tail.zipBy(_2().tail)(_3) }
}
