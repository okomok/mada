

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
class FromArray[A](_1: Array[A]) extends Forwarder[A] {
    override protected val delegate = from(_1)
}


private
class FromTraversable[+A](_1: scala.collection.Traversable[A]) extends Reactive[A] {
    override def forloop(f: A => Unit, k: Exit => Unit) {
        try {
            _1.foreach(f)
        } catch {
            case t: Throwable => {
                k(Thrown(t))
                throw t
            }
        }
        k(End)
    }
}


private
class FromOption[+A](_1: Option[A]) extends Reactive[A] {
    override def forloop(f: A => Unit, k: Exit => Unit) {
        try {
            if (!_1.isEmpty) {
                f(_1.get)
            }
        } catch {
            case t: Throwable => {
                k(Thrown(t))
                throw t
            }
        }
        k(End)
    }
}


private
class FromResponder[+A](_1: Responder[A]) extends Reactive[A] {
    override def forloop(f: A => Unit, k: Exit => Unit) {
        try {
            _1.respond(f)
        } catch {
            case t: Throwable => {
                k(Thrown(t))
                throw t
            }
        }
        k(End)
    }
}

private
case class ToResponder[+A](_1: Reactive[A]) extends Responder[A] {
    override def respond(f: A => Unit) = _1.foreach(f)
}
