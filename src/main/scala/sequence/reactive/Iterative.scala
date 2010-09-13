

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private[reactive]
case class FromIterative[+A](_1: Iterative[A]) extends Reactive[A] {
    override def foreach(f: A => Unit) = for (x <- _1) f(x)
}


private[reactive]
case class ToIterative[A](_1: Reactive[A]) extends iterative.Forwarder[A] {
    private val arr = new java.util.ArrayList[A]
    for (x <- _1) arr.add(x)
    override protected val delegate = Iterative.from(arr)
}
