

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector.parallels


case class Filter[A](_1: Vector[A], _2: A => Boolean, _3: Int) extends Forwarder[A] {
    util.assert(!isParallel(_1))
    override protected val delegate = _1.copy.parallel(_3).mutatingFilter(_2).readOnly
}
