

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object stack {

    @aliasOf("Stack")
    type Type[A] = Stack[A]

    @conversion
    def from[A](from: Compatible[A]): Stack[A] = from._1

    @conversion
    def fromJDeque[A](from: java.util.Deque[A]): Stack[A] = FromJDeque(from)

    @conversion
    def fromSStack[A](from: scala.collection.mutable.Stack[A]): Stack[A] = FromSStack(from)

    @conversion
    def fromSArrayStack[A](from: scala.collection.mutable.ArrayStack[A]): Stack[A] = FromSArrayStack(from)

}
