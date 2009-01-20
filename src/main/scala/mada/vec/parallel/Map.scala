

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


import scala.actors.Futures


object Map {
    def apply[Z, A](v: Vector[Z], f: Z => A, grainSize: Long): Vector[A] = new MapVector(v, f, grainSize)
}

class MapVector[Z, A](v: Vector[Z], f: Z => A, grainSize: Long) extends VectorProxy[A] with NotWritable[A] {
    override lazy val self = make.parallel(grainSize)

    override def map[B](_f: A => B) = v.parallel(grainSize).map(_f compose f) // map-map fusion

    private def make = {
        if (grainSize == 1) {
            v.map({ e => Futures.future(f(e)) }).force.map({ u => u() })
        } else {
            Vector.undivide(
                v.divide(grainSize).map({ w => Futures.future(w.map(f)) }).force.map({ u => u() })
            )
        }
    }
}
