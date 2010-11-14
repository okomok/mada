

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package stack


object Stack extends Common with Compatibles


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
    def clear(): Unit

    @conversion
    def toSStack: scala.collection.mutable.Stack[A] = throw new Error

    @aliasOf("peek")
    final def top: A = peek

    @aliasOf("size")
    final def length: Int = size

}
