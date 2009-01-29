

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


/**
 * Represents subvector.
 */
case class SubVector[A](self: Vector[A], override val start: Int, override val end: Int) extends VectorAdapter[A, A] {
    override def * = self

    override def subVector(_start: Int, _end: Int) = self.subVector(_start, _end) // subVector-subVector fusion
}
