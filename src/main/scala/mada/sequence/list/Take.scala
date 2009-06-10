

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.list


case class Take[+A](_1: List[A], _2: Int) extends Forwarder[A] {
    Precondition.nonnegative(_2, "take")

    override protected lazy val delegate: List[A] = {
        if (_2 <= 0) {
            nil
        } else if (_1.isNil) {
            nil
        } else {
            cons(_1.head, _1.tail.take(_2 - 1))
        }
    }

    override def take(n: Int) = _1.take(Math.max(_2, n)) // take-take fusion
}
