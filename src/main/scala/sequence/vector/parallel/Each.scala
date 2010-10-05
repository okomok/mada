

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package vector


import util.future


private
object ParallelEach {
    def apply[A](_1: Vector[A], _2: A => Unit, _3: Int): Unit = {
        assert(!IsParallel(_1))

        if (_3 == 1) {
            _1.map{ e => future(_2(e)) }.force.foreach{ u => u() }
        } else {
            _1.divide(_3).map{ w => future(w.foreach(_2)) }.
                force. // start tasks.
                    foreach{ u => u() } // join all.
        }
    }
}
