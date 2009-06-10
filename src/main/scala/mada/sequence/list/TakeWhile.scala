

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.list


case class TakeWhile[A](_1: List[A], _2: A => Boolean) extends Forwarder[A] {
    override protected val delegate: List[A] = {
        if (_1.isNil) {
            nil
        } else if (_2(_1.head)) {
            cons(_1.head, _1.tail.takeWhile(_2))
        } else {
            nil
        }
    }
}
