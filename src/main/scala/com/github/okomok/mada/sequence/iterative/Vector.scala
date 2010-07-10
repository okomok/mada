

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package iterative


private[mada] case class ToVector[A](_1: Iterative[A]) extends vector.Forwarder[A] {
    override protected val delegate = {
        val it = _1.begin
        val a = new java.util.ArrayList[A]
        while (it) {
            a.add(~it)
            it.++
        }
        Vector.from(a)
    }
}
