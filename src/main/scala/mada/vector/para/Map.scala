

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector.para


private[mada] object Map {
    def apply[Z, A](v: Vector[Z], f: Z => A, grainSize: Int): Vector[A] = new MapVector(v, f, grainSize)
}

private[mada] class MapVector[Z, A](v: Vector[Z], f: Z => A, grainSize: Int) extends Forwarder[A] {
    util.assert(!IsParallel(v))
    import function.future

    override lazy val delegate = {
        if (grainSize == 1) {
            v.map{ e => future(f(e)) }.force.map{ u => u() }
        } else {
            vector.undivide(
                v.divide(grainSize).map{ w => future(w.map(f).force) }.
                    force. // start tasks.
                        map{ u => u() } // get result by projection.
            )
        }
    }

    override def memoize = delegate // memoize-map fusion
//    override def map[B](_f: A => B) = v.parallel(grainSize).map(_f compose f) // map-map fusion
//    override def seek(p: A => Boolean) = v.parallel(grainSize).seek(p compose f).map(f) // seek-map fusion

    // parallel.reduce is implemented by assoc.reduce.
    // override def reduce(op: (A, A) => A) = v.map(f).parallel(grainSize).reduce(op) // reduce-map fusion
}
