

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class Map[Z, A](_1: Vector[Z], _2: Z => A) extends Adapter[Z, A] with NotWritable[A] {
    override protected val underlying = _1

    override def apply(i: Int) = _2(underlying(i))

    override def map[B](_f: A => B) = underlying.map(_f compose _2) // map-map fusion
    override def loop[F <: (A => Boolean)](i: Int, j: Int, _f: F) = { underlying.loop(i, j, _f compose _2); _f } // loop-map fusion
}
