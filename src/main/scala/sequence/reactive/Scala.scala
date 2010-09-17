

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class FromArray[A](_1: Array[A]) extends Forwarder[A] {
    override protected val delegate = fromSIterable(_1)
}

private
case class FromSIterable[+A](_1: Iterable[A]) extends Reactive[A] {
    override def foreach(f: A => Unit) = _1.foreach(f)
}
