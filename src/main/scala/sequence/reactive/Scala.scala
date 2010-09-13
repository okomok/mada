

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private[reactive]
case class FromArray[A](_1: Array[A]) extends Forwarder[A] {
    override protected val delegate = fromSIterable(_1)
}

private[reactive]
case class FromSIterable[+A](_1: Iterable[A]) extends Reactive[A] {
    override def foreach(f: A => Unit) = for (x <- _1) f(x)
}
