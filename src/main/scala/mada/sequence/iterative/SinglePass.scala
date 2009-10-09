

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package iterative


case class SinglePass[+A](_1: Iterative[A]) extends Iterative[A] {
    override lazy val begin = _1.begin
}
