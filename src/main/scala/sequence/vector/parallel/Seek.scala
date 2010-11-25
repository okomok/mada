

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


private
object ParallelSeek {
    def apply[A](_1: Vector[A], _2: A => Boolean, _3: Int): Option[A] = {
        assert(!IsParallel(_1))

        if (_1.isEmpty) {
            None
        } else {
            var r: Option[A] = None
            val p = new Breakable1(_2, true)
            _1.divide(_3).parallelBy(1).each { v =>
                val x = v.seek(p)
                if (!x.isEmpty && !p.breaks) {
                    // benign data races
                    r = x
                    p.break()
                }
            }
            r
        }
    }
}
