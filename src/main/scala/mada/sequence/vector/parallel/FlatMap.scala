

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class ParallelFlatMap[Z, A](_1: Vector[Z], _2: Z => Vector[A], _3: Int) extends Forwarder[A] {
    util.assert(!isParallel(_1))
    override protected val delegate = flatten(_1.parallel(_3).map(_2))
}
