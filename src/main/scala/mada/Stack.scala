

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import stack._


/**
 * Contains utility methods operating on <code>Stack</code>.
 */
object Stack extends Aliases with Conversions with Compatibles {
    /**
     * Triggers implicit conversions explicitly.
     *
     * @return  <code>to</code>.
     */
    def from[A](to: Stack[A]) = to
}


/**
 * Trivial stack interface
 */
trait Stack[A] {

    @returncompanion def companion = Stack

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

}
