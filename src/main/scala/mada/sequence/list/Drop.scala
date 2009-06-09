

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.list


case class Drop[+A](_1: List[A], _2: Int) extends Forwarder[A] {
    override protected val delegate = throw new Error//if (_1.isNil || n <= 0) _1 else _1.drop(n - 1)
}
