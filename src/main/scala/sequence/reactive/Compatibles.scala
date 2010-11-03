

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


@compatibles
trait Compatibles {
    implicit def fromArray[A](from: Array[A]): Reactive[A] = new FromArray(from)
    implicit def fromTraversable[A](from: scala.collection.Traversable[A]): Reactive[A] = new FromTraversable(from)
    implicit def fromResponder[A](from: Responder[A]): Reactive[A] = FromResponder(from)
    implicit def fromReactor(from: Reactor): Reactive[Any] = Reactor.Secondary(from)
}
