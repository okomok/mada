

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.list


case class Map[Z, A](_1: List[Z], _2: Z => A) extends Forwarder[A] {
    override protected val delegate = if (_1.isNil) nil else cons(_2(_1.head), _1.tail.map(_2))
}

/*
case class Map[Z, A](_1: List[Z], _2: Z => A) extends List[A] {
    override def isNil = _1.isNil
    override def head = _2(_1.head)
    override def tail = Map(_1.tail, _2)
}
*/
