

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


private[mada] object Map {
    def apply[Z, A](v: Vector[Z], f: Z => A): Vector[A] = new MapVector(v, f)
}

private[mada] class MapVector[Z, A](override val underlying: Vector[Z], f: Z => A) extends Adapter[Z, A] with Adapter.NotWritable[A] {
    override def apply(i: Int) = f(underlying(i))

    override def map[B](_f: A => B) = underlying.map(_f compose f) // map-map fusion
    override def loop[F <: (A => Boolean)](i: Int, j: Int, _f: F) = { underlying.loop(i, j, _f compose f); _f } // loop-map fusion
}
