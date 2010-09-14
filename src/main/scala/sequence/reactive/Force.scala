

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private[reactive]
case class Force[+A](_1: Reactive[A]) extends Reactive[A] {
    private val r = {
        val arr = new java.util.ArrayList[A]
        for (x <- _1) arr.add(x)
        fromIterative(Iterative.from(arr))
    }
    override def foreach(f: A => Unit) = r.foreach(f)
    override def force: Reactive[A] = this // force-force fusion
}
