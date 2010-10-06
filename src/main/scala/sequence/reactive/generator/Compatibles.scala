

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive; package generator


@compatibles
trait Compatibles {
    implicit def fromArray[A](from: Array[A]): Generator[A] = new FromSIterable(from)
    implicit def fromSIterable[A](from: Iterable[A]): Generator[A] = new FromSIterable(from)
}
