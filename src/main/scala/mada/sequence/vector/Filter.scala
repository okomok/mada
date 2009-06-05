

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class Filter[A](_1: Vector[A], _2: A => Boolean) extends Forwarder[A] {
    override protected val delegate = _1.copy.mutatingFilter(_2).readOnly
    override def filter(_p: A => Boolean) = _1.filter{ e => _2(e) && _p(e) } // filter-filter fusion
}

case class Remove[A](_1: Vector[A], _2: A => Boolean) extends Forwarder[A] {
    override protected val delegate = _1.filter(function.not(_2))
}
