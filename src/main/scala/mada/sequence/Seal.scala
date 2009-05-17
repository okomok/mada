

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


case class Seal[+A](_1: Sequence[A]) extends Sequence[A] {
    override def begin = _1.begin
}
