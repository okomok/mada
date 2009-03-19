

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import stack._


/**
 * Contains utility methods operating on <code>Stack</code>.
 */
object Stack extends Conversions with Compatibles {
    /**
     * Alias of <code>stack.StackProxy</code>
     */
    type Forwarder[A] = stack.StackProxy[A]

    /**
     * Alias of <code>stack.StackProxy</code>
     */
    type StackProxy[A] = stack.StackProxy[A]

    /**
     * Triggers implicit conversions explicitly.
     *
     * @return  <code>to</code>.
     */
    def from[A](to: Stack[A]) = to

    /**
     * @return  <code>this</code>.
     */
    val Compatibles: stack.Compatibles = this

    /**
     * Alias of <code>Stack</code>
     */
    type Type[A] = Stack[A]
}


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

    /**
     * Alias of <code>peek</code>
     */
    final def top: A = peek

    /**
     * Alias of <code>size</code>
     */
    final def length: Int = size

    /**
     * Alias of <code>Stack</code>
     */
    final def companion = Stack
}
