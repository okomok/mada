

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.stack


/**
 * Contains implicit conversions around <code>Stack</code>.
 */
trait Compatibles {
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

    /**
     * Converts <code>scala.collection.mutable.ArrayStack</code> to <code>Stack</code>.
     */
    implicit def madaStackFromSclArrayStack[A](from: scala.collection.mutable.ArrayStack[A]) = new Stack[A] {
        override def push(e: A) = from.push(e)
        override def pop = from.pop
        override def peek = from.peek
        override def size = from.size
        override def isEmpty = from.isEmpty
        override def clear = from.clear
    }
}
