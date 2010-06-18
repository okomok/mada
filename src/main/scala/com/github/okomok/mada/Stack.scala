

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada


import stack._


/**
 * Trivial stack interface
 */
trait Stack[A] {

    /**
     * Pushes.
     */
    def push(e: A): Unit

    /**
     * Pops.
     */
    def pop: A

    /**
     * Peek of the stack
     */
    def peek: A

    /**
     * Is this stack empty?
     */
    def isEmpty: Boolean

    /**
     * Size of the stack
     */
    def size: Int

    /**
     * Clears this stack.
     */
    def clear: Unit

    @compatibleConversion
    def toSome: ToSome[A] = new ToSome(this)

    @compatibleConversion
    def toSStack: scala.collection.mutable.Stack[A] = throw new Error

    @aliasOf("peek")
    final def top: A = peek

    @aliasOf("size")
    final def length: Int = size

}

object Stack {
    implicit def _fromJDeque[A](from: java.util.Deque[A]): Stack[A] = fromJDeque(from)
    implicit def _fromSStack[A](from: scala.collection.mutable.Stack[A]): Stack[A] = fromSStack(from)
    implicit def _fromSArrayStack[A](from: scala.collection.mutable.ArrayStack[A]): Stack[A] = fromSArrayStack(from)
}
