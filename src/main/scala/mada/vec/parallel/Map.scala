

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


object Map {
    def apply[Z, A](v: Vector[Z], f: Z => A, grainSize: Int): Vector[A] = new MapVector(v, f, grainSize)
}

class MapVector[Z, A](v: Vector[Z], f: Z => A, grainSize: Int) extends VectorProxy[A] with NotWritable[A] {
    Assert(!IsParallelVector(v))
    override lazy val self = {
        if (grainSize == 1) {
            v.map({ e => Future(f(e)) }).force.map({ u => u() })
        } else {
            Vector.undivide(
                v.divide(grainSize).map({ w => Future(w.map(f)) }).force.map({ u => u() })
            )
        }
    }

    override def force = _wait(self) // force-map fusion
    override def lazyValues = self // lazyValues-map fusion
    override def map[B](_f: A => B) = v.parallel(grainSize).map(_f compose f) // map-map fusion
    override def seek(p: A => Boolean) = v.parallel(grainSize).seek(p compose f).map(f) // seek-map fusion

    private def _wait(v: Vector[A]): Vector[A] = {
        Assert(!IsParallelVector(v))
        v.foreach({ (_: A) => () })
        v
    }
}
