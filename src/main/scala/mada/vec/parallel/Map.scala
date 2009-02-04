

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


private[mada] object Map {
    def apply[Z, A](v: Vector[Z], f: Z => A, grainSize: Int): Vector[A] = new MapVector(v, f, grainSize)
}

private[mada] class MapVector[Z, A](v: Vector[Z], f: Z => A, grainSize: Int) extends Adapter.Proxy[A] with NotWritable[A] {
    Assert(!v.isParallel)

    override lazy val unparallel = {
        if (grainSize == 1) {
            v.map({ e => Future(f(e)) }).force.map({ u => u() })
        } else {
            Vector.undivide(
                v.divide(grainSize).map({ w => Future(w.map(f).force) }).force.map({ u => u() })
            )
        }
    }
    override lazy val self = unparallel.parallel(grainSize)

    override def lazyValues = self // lazyValues-map fusion
    override def map[B](_f: A => B) = v.parallel(grainSize).map(_f compose f) // map-map fusion
    override def reduce(op: (A, A) => A) = v.map(f).parallel(grainSize).reduce(op) // reduce-map fusion
    override def seek(p: A => Boolean) = v.parallel(grainSize).seek(p compose f).map(f) // seek-map fusion
}
