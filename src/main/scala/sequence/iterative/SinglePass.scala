

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package iterative


private
case class SinglePass[+A](_1: Iterative[A]) extends Iterative[A] {
    override val begin = _1.begin
}
