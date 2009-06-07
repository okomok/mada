

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


private object ParallelEqualsIf {
    def apply[A, B](_1: Vector[A], _2: Vector[B], _3: (A, B) => Boolean, _4: Int): Boolean = {
        util.assert(!IsParallel(_1))

        if (_1.size != _2.size) {
            false
        } else {
            val bp = new Breakable2(_3, false)
            (_1.divide(_4) zip _2.divide(_4)).
                parallel(1).map{ case (v1, w1) => breakingEquals(v1, w1, bp) }.
                    reduce(_ && _)
        }
    }

    private def breakingEquals[A, B](v: Vector[A], w: Vector[B], p: Breakable2[A, B]): Boolean = {
        val x = v.equalsIf(w)(p)
        if (!x) {
            p.break
        }
        x
    }
}
