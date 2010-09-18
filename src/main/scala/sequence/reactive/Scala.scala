

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
class FromArray[A](_1: Array[A]) extends Forwarder[A] {
    override protected val delegate = from(_1)
}


private
class FromSIterable[+A](_1: Iterable[A]) extends Reactive[A] {
    override def foreach(f: A => Unit) = _1.foreach(f)
}


private
case class FromResponder[+A](_1: Responder[A]) extends Reactive[A] {
    override def foreach(f: A => Unit) = _1.respond(f)
}

private
case class ToResponder[+A](_1: Reactive[A]) extends Responder[A] {
    override def respond(f: A => Unit) = _1.foreach(f)
}
