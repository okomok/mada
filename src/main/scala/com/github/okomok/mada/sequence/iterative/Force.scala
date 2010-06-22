

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package iterative


@visibleForTesting
case class Force[+A](_1: Iterative[A]) extends Iterative[A] {
    private val k = { // should not be lazy; that's "force".
        val r = new java.util.ArrayList[A]
        val it = _1.begin
        while (it) {
            r.add(~it)
            it.++
        }
        fromJIterable(r)
    }
    override def begin = k.begin

    override def force: Iterative[A] = this // force-force fusion
}
