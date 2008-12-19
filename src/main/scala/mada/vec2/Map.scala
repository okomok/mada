

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec2


class MapVector[Z, A](override val * : Vector[Z], f: Z => A) extends Adapter[Z, A] with NotWritable[A] {
    override def apply(i: Long) = f(*(i))

    override def map[B](_f: A => B) = *.map(_f compose f)
    override def loop[F <: (A => Boolean)](_f: F) = { *.loop(_f compose f); _f }
}
