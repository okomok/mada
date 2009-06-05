

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class ParallelFolder[A](_1: Vector[A], _2: A, _3: (A, A) => A, _4: Int) extends Forwarder[A] {
    util.assert(!isParallel(_1))
    override protected val delegate = (single(_2) ++ _1).parallel(_4).reducer(_3)
}


case class ParallelReducer[A](_1: Vector[A], _2: (A, A) => A, _3: Int) extends Forwarder[A] {
    util.assert(!isParallel(_1))
    precondition.notEmpty(_1, "paralell.reducer")

    override protected val delegate = {
        val rss = _1.parallelRegions(_3).map{ w => w.reducer(_2) }
        if (rss.size == 1) {
            rss.head
        } else {
            val ls = rss.init.map{ w => w.last }.reducer(_2)
            rss.head ++ (ls zip rss.tail).parallel(1).map{ case (l, rs) => rs.map{ r => _2(l, r) } }.undivide
        }
    }
}
