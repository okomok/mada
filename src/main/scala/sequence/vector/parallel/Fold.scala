

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


private
object ParallelFold {
    def apply[A](_1: Vector[A], _2: A, _3: (A, A) => A, _4: Int): A = {
        assert(!IsParallel(_1))
        (single(_2) ++ _1).parallelBy(_4).reduce(_3)
    }
}

private
object ParallelReduce {
    def apply[A](_1: Vector[A], _2: (A, A) => A, _3: Int): A = {
        assert(!IsParallel(_1))
        Precondition.notEmpty(_1, "paralell.reduce")
        _1.divide(_3).parallelBy(1).map(_.reduce(_2)).reduce(_2)
    }
}


private
case class ParallelFolder[A](_1: Vector[A], _2: A, _3: (A, A) => A, _4: Int) extends Forwarder[A] {
    assert(!IsParallel(_1))
    override protected val delegate = (single(_2) ++ _1).parallelBy(_4).reducer(_3)
}

private
case class ParallelReducer[A](_1: Vector[A], _2: (A, A) => A, _3: Int) extends Forwarder[A] {
    assert(!IsParallel(_1))
    Precondition.notEmpty(_1, "paralell.reducer")

    override protected val delegate = {
        val rss = _1.divide(_3).parallelBy(1).map(_.reducer(_2))
        if (rss.size == 1) {
            rss.head
        } else {
            val ls = rss.init.map{ w => w.last }.reducer(_2)
            rss.head ++ (ls zip rss.tail).parallelBy(1).map{ case (l, rs) => rs.map{ r => _2(l, r) } }.undivide
        }
    }
}
