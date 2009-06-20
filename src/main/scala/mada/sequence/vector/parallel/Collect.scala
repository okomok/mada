

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class ParallelCollect[Z, A](_1: Vector[Z], _2: Vector[Z] => A, _3: Int) extends Forwarder[A] {
    util.assert(!IsParallel(_1))

    override protected val delegate = {
        _1.divide(_3).map{ w => util.future(_2(w)) }.
            force. // start tasks.
                map{ u => u() }. // get result.
                    force // join!
    }
}


case class ParallelZipCollect[Z1, Z2, A](_1: Vector[Z1], _2: Vector[Z2], _3: (Vector[Z1], Vector[Z2]) => A, _4: Int) extends Forwarder[A] {
    util.assert(!IsParallel(_1))

    override protected val delegate = {
        (_1.divide(_4) zip _2.divide(_4)).map{ case (v1, v2) => util.future(_3(v1, v2)) }.
            force. // start tasks.
                map{ u => u() }. // get result.
                    force // join!
    }
}


/*
case class ParallelMap[Z, A](_1: Vector[Z], _2: Z => A, _3: Int) extends Forwarder[A] {
    util.assert(!IsParallel(_1))

    override protected val delegate = {
        if (_3 == 1) {
            _1.map{ e => future(_2(e)) }.force.map{ u => u() }
        } else {
            _1.divide(_3).map{ w => future(w.map(_2).force) }.
                force. // start tasks.
                    map{ u => u() }. // get result by projection.
                        undivide
        }
    }

    override def memoize = delegate // memoize-map fusion
//    override def map[A](_f: A => A) = _1.parallel(_3).map(_f compose _2) // map-map fusion
//    override def seek(p: A => Boolean) = _1.parallel(_3).seek(p compose _2).map(_2) // seek-map fusion

    // Impossible: parallel.reduce is implemented by map-reduce.
    // override def reduce(op: (A, A) => A) = _1.map(_2).parallel(_3).reduce(op) // reduce-map fusion
}
*/
