

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package peg


private
case class Unmap[A, Z](_1: Peg[A], _2: Z => A) extends Forwarder[Z] {
    override protected val delegate = _1.readMap{ (v: sequence.Vector[Z]) => v.map(_2) }
}
