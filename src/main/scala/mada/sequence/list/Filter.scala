

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.list


case class Filter[A](_1: List[A], _2: A => Boolean) extends Forwarder[A] {
    override protected val delegate = {
        if (_1.isNil) {
            _1
        } else if (_2(_1.head)) {
            cons(_1.head, _1.tail.filter(_2))
        } else {
            _1.tail.filter(_2)
        }
    }
}
