

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package vector


case class ParallelCopy[A](_1: Vector[A], _2: Int) extends Forwarder[A] {
    util.assert(!IsParallel(_1))

    override protected val delegate = {
        val r = newArray[A](_1.size)
        _1.parallel(_2).copyTo(fromArray(r))
        fromArray(r)
    }
}

private object ParallelCopyTo {
    def apply[A, B >: A](_1: Vector[A], _2: Vector[B], _3: Int): Vector[B] = {
        util.assert(!IsParallel(_1))
        Precondition.range(_1.size, _2.size, "parallel.copyTo")

        (_1.divide(_3) zip _2.divide(_3)).parallel(1).each{ case (v1, w1) => v1.copyTo(w1) }
        _2.window(0, _1.size)
    }
}
