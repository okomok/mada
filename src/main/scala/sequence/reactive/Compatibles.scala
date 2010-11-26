

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


@annotation.compatibles
trait Compatibles {
    implicit def fromArray[A](from: Array[A]): Reactive[A] = new FromArray(from)
    implicit def fromTraversable[A](from: scala.collection.Traversable[A]): Reactive[A] = new FromTraversable(from)
    implicit def fromOption[A](from: Option[A]): Reactive[A] = new FromOption(from)
    implicit def fromResponder[A](from: Responder[A]): Reactive[A] = new FromResponder(from)
    implicit def fromReactor(from: Reactor): Reactive[Any] = Reactor.Secondary(from)
//    implicit def fromCps[A](from: => A @scala.util.continuations.suspendable): Reactive[A] = new FromCps(from) // doesn't work.
}
