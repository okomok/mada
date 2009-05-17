

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object stack {

    @aliasOf("Stack")
    type Type[A] = Stack[A]

    @returnThat
    def from[A](to: Stack[A]) = to

    @compatibles
    val compatibles: Compatibles = Stack

    @conversion
    def fromJDeque[A](from: java.util.Deque[A]): Stack[A] = new Stack[A] {
        override def push(e: A) = from.push(e)
        override def pop = from.pop
        override def peek = from.peek
        override def size = from.size
        override def isEmpty = from.isEmpty
        override def clear = from.clear
    }

    @conversion
    def fromSStack[A](from: scala.collection.mutable.Stack[A]): Stack[A] = new Stack[A] {
        override def push(e: A) = from.push(e)
        override def pop = from.pop
        override def peek = from.top
        override def size = from.length
        override def isEmpty = from.isEmpty
        override def clear = from.clear
    }

    @conversion
    def fromSArrayStack[A](from: scala.collection.mutable.ArrayStack[A]): Stack[A] = new Stack[A] {
        override def push(e: A) = from.push(e)
        override def pop = from.pop
        override def peek = from.peek
        override def size = from.size
        override def isEmpty = from.isEmpty
        override def clear = from.clear
    }

}
