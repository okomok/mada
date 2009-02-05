

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains implicit conversions around <code>Stack</code>.
 */
trait StackCompatibles {
    /**
     * @return  <code>this</code>.
     */
    val madaStackCompatibles: StackCompatibles = this

    /**
     * Converts <code>java.util.Deque</code> to <code>Stack</code>.
     */
    implicit def madaStackFromJclDeque[A](from: java.util.Deque[A]) = new Stack[A] {
        override def push(e: A) = from.push(e)
        override def pop = from.pop
        override def peek = from.peek
        override def size = from.size
        override def isEmpty = from.isEmpty
        override def clear = from.clear
    }

    /**
     * Converts <code>scala.collection.mutable.Stack</code> to <code>Stack</code>.
     */
    implicit def madaStackFromSclStack[A](from: scala.collection.mutable.Stack[A]) = new Stack[A] {
        override def push(e: A) = from.push(e)
        override def pop = from.pop
        override def peek = from.top
        override def size = from.length
        override def isEmpty = from.isEmpty
        override def clear = from.clear
    }
}


/**
 * Contains utility methods operating on <code>Stack</code>.
 */
object Stack extends StackCompatibles {
    /**
     * Alias of <code>madaStackCompatibles</code>
     */
    val Compatibles = madaStackCompatibles

    /**
     * Triggers implicit conversions explicitly.
     *
     * @return  <code>to</code>.
     */
    def from[A](to: Stack[A]) = to

    /**
     * Alias of <code>madaStackFromJclDeque</code>
     */
    def fromJclDeque[A](from: java.util.Deque[A]) = madaStackFromJclDeque(from)

    /**
     * Alias of <code>madaStackFromSclDeque</code>
     */
    def fromSclStack[A](from: scala.collection.mutable.Stack[A]) = madaStackFromSclStack(from)
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
}
