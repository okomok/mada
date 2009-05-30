

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative


case class Seal[+A](_1: Iterative[A]) extends Iterative[A] {
    override def begin = _1.begin
}
