

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class SinglePass[A](_1: Traversable[A]) extends Traversable[A] {
    override val start = _1.start
}
