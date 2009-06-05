

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class Seal[A](_1: Vector[A]) extends TransformAdapter[A] {
    override protected val underlying = _1

    // seal-seal fusion too must be "sealed".
}
