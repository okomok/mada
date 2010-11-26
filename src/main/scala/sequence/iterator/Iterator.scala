

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterator


object Iterator extends Common {
    implicit def _toBoolean[A](from: Iterator[A]): Boolean = from.toBoolean
}


/**
 * Yet another Iterator: backend of <code>Iterative</code>.
 * Unlike <code>Iterator</code>, this separates element-access and traversing method.
 * (E.g. <code>.map(f).length</code> is inefficient in scala.Iterator abstraction.)
 */
@annotation.notThreadSafe
trait Iterator[+A] {

    /**
     * Is iterator pass-the-end?
     */
    def isEnd: Boolean

    /**
     * Returns the current element.
     */
    def deref: A

    /**
     * Traverses to the next position.
     */
    def increment(): Unit

    @annotation.aliasOf("isEnd")
    final def unary_! = isEnd

    @annotation.aliasOf("deref")
    final def unary_~ = deref

    @annotation.aliasOf("increment")
    final def ++ = increment()

    @annotation.conversion @annotation.aliasOf("!isEnd")
    final def toBoolean: Boolean = !isEnd

    @annotation.conversion
    final def toSIterator: scala.Iterator[A] = ToSIterator(this)

    /**
     * Advances <code>n</code>.
     */
    final def advance(n: Int) {
        var it = n
        while (it != 0 && !isEnd) {
            increment()
            it -= 1
        }
    }

    /**
     * Advances while satisfying the predicate.
     */
    final def advanceWhile(p: A => Boolean) {
        while (!isEnd && p(deref)) {
            increment()
        }
    }
/*
    // (Probably efficiently) compiles without:
    final def &&(p: => Boolean): Boolean = if (!isEnd) p else false
    final def ||(p: => Boolean): Boolean = if (!isEnd) true else p
*/
}
