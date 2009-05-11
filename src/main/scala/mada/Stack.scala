

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


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

    @aliasOf("peek")
    final def top: A = peek

    @aliasOf("size")
    final def length: Int = size

}


object Stack extends Compatibles {

    @returnThis
    val compatibles: Compatibles = this

}
