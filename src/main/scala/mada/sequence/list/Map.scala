

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.list


case class Map[A, B](_1: List[A], _2: A => B) extends List[B] {
    override def isEmpty = _1.isEmpty
    override def head = _2(_1.head)
    override def tail = _1.tail.map(_2)
}
