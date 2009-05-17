

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


case class SinglePass[+A](_1: Sequence[A]) extends Sequence[A] {
    override val begin = _1.begin
}
