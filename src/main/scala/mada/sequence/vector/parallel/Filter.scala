

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class ParallelFilter[A](_1: Vector[A], _2: A => Boolean, _3: Int) extends Forwarder[A] {
    util.assert(!IsParallel(_1))
    override protected val delegate = _1.copy.parallel(_3).mutatingFilter(_2).readOnly
}

private object ParallelMutatingFilter {
    def apply[A](_1: Vector[A], _2: A => Boolean, _3: Int): Vector[A] = {
        util.assert(!IsParallel(_1))
        flatten(_1.parallelRegions(_3).map{ w => w.mutatingFilter(_2).toIterative })
    }
}
