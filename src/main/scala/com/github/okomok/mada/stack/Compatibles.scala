

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package stack


@compilerWorkaround("2.8.0") // can't be placed in `Common`; stack namespace is so wrongly? associated that ambiguity error occurs.
private[mada] trait Compatibles {

    @compatibleConversion
    implicit def fromJDeque[A](from: java.util.Deque[A]): Stack[A] = FromJDeque(from)

    @compatibleConversion
    implicit def fromSStack[A](from: scala.collection.mutable.Stack[A]): Stack[A] = FromSStack(from)

    @compatibleConversion
    implicit def fromSArrayStack[A](from: scala.collection.mutable.ArrayStack[A]): Stack[A] = FromSArrayStack(from)

}
