

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


private
case class Copy[A](_1: Vector[A]) extends Forwarder[A] {
    override protected val delegate = {
        val r = allocate[A](_1.size)
        _1.copyTo(r)
        r
    }

    // Comment-out: force should remove any indirection.
    // override def force: Vector[A] = readOnly // copy.force fusion
}
