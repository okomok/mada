

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


import eval.Future


// Note that parallel.map is projection, that is, non-blocking.
private
case class ParallelMap[Z, A](_1: Vector[Z], _2: Z => A, _3: Int) extends Forwarder[A] {
    assert(!IsParallel(_1))

    override protected val delegate = {
        if (_3 == 1) {
            _1.map{ e => Future(_2(e)) }.force.map{ u => u() }
        } else {
            _1.divide(_3).map{ w => Future(w.map(_2).force) }.
                force. // start tasks.
                    map{ u => u() }. // get result by projection.
                        undivide
        }
    }

    override def memoize = delegate // map.memoize fusion
//    override def map[A](_f: A => A) = _1.parallelBy(_3).map(_f compose _2) // map.map fusion
//    override def seek(p: A => Boolean) = _1.parallelBy(_3).seek(p compose _2).map(_2) // map.seek fusion

    // Impossible: parallel.reduce is implemented by map-reduce.
    // override def reduce(op: (A, A) => A) = _1.map(_2).parallelBy(_3).reduce(op) // map.reduce fusion
}
