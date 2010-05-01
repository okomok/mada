

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada


package object stack {

    @aliasOf("Stack")
    type Type[A] = Stack[A]

    @compatibleConversion
    def from[A](to: Stack[A]): Stack[A] = to

    @compatibleConversion
    def fromJDeque[A](from: java.util.Deque[A]): Stack[A] = FromJDeque(from)

    @compatibleConversion
    def fromSStack[A](from: scala.collection.mutable.Stack[A]): Stack[A] = FromSStack(from)

    @compatibleConversion
    def fromSArrayStack[A](from: scala.collection.mutable.ArrayStack[A]): Stack[A] = FromSArrayStack(from)

}
