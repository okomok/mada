

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class Times[A](_1: Traversable[A], _2: Int) extends Forwarder[A] {
    override protected val delegate = repeat(()).take(_2).flatMap{ (u: Unit) => _1 }
}
