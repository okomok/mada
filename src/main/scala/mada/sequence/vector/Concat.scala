

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class Concat[A](_1: scala.collection.Sequence[Vector[A]]) extends Forwarder[A] {
    override protected val delegate = _1.foldLeft(empty[A]){ (r, v) => r ++ v }
}
