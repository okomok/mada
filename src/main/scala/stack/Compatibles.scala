

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package stack


@annotation.compilerWorkaround("2.8.0") // can't be placed in `Common`; stack namespace is so wrongly? associated that ambiguity error occurs.
@annotation.compatibles
trait Compatibles {
    implicit def fromJDeque[A](from: java.util.Deque[A]): Stack[A] = FromJDeque(from)
    implicit def fromSStack[A](from: scala.collection.mutable.Stack[A]): Stack[A] = FromSStack(from)
    implicit def fromSArrayStack[A](from: scala.collection.mutable.ArrayStack[A]): Stack[A] = FromSArrayStack(from)
}
