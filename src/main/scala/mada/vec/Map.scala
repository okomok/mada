

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Map {
    def apply[Z, A](v: Vector[Z], f: Z => A): Vector[A] = new MapVector(v, f)
}

class MapVector[Z, A](override val * : Vector[Z], f: Z => A) extends VectorAdapter[Z, A] with NotWritable[A] {
    override def apply(i: Long) = f(*(i))

    override def map[B](_f: A => B) = *.map(_f compose f) // map-map fusion
    override def loop[F <: (A => Boolean)](i: Long, j: Long, _f: F) = { *.loop(i, j, _f compose f); _f } // loop-map fusion
}
