

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package peg


private
case class FilterFrom[A](_1: Peg[A], _2: sequence.Vector[A]) extends sequence.iterative.Forwarder[A] {
    override protected val delegate = _1.tokenize(_2).flatten
}
