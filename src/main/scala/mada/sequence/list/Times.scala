

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.list


case class Times[+A](_1: List[A], _2: Int) extends Forwarder[A] {
    override protected val delegate = throw new Error//repeat(()).take(_2).flatMap{ (u: Unit) => _1 }
}
