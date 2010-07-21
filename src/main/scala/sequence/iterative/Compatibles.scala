

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package iterative


@compatibles
trait Compatibles {
    implicit def unstringize(from: String): Iterative[Char] = Unstringize(from)
    implicit def fromArray[A](from: Array[A]): Iterative[A] = FromArray(from)
    implicit def fromOption[A](from: Option[A]): Iterative[A] = FromOption(from)
    implicit def fromSIterable[A](from: Iterable[A]): Iterative[A] = FromSIterable(from)
    implicit def fromJIterable[A](from: java.lang.Iterable[A]): Iterative[A] = FromJIterable(from)
    implicit def fromJObjectInput(from: java.io.ObjectInput): Iterative[AnyRef] = FromJObjectInput(from)
    implicit def fromJReader(from: java.io.Reader): Iterative[Char] = FromJReader(from)
}
