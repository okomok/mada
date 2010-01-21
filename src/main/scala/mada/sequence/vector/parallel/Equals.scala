

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package vector


private object ParallelEqualsIf {
    def apply[A, B](_1: Vector[A], _2: Vector[B], _3: (A, B) => Boolean, _4: Int): Boolean = {
        util.assert(!IsParallel(_1))

        if (_1.size != _2.size) {
            false
        } else {
            val p = new Breakable2(_3, false)
            (_1.divide(_4) zip _2.divide(_4)).parallel(1).map { case (v, w) =>
                val x = v.equalsIf(w)(p)
                if (!x) { p.break }
                x
            }.reduce(_ && _) // force to join all.
        }
    }
}
