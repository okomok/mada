

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import traverser._


/**
 * Yet another Iterator: backend of <code>Traversable</code>.
 * Unlike <code>Iterator</code>, this separates element-access and traversing method.
 * (E.g. <code>.map(f).length</code> is inefficient in SIterator abstraction.)
 */
@notThreadSafe
trait Traverser[+A] {

    /**
     * Is traverser pass-the-end?
     */
    def isEnd: Boolean

    /**
     * Returns the current element.
     */
    def deref: A

    /**
     * Traverses to the next position.
     */
    def increment: Unit

    @aliasOf("isEnd")
    final def unary_! = isEnd

    @aliasOf("deref")
    final def unary_~ = deref

    @aliasOf("increment")
    final def ++ = increment

    // Do you know NVI pattern?
    protected def preDeref: Unit = if (isEnd) throw new NoSuchElementException("deref on end traverser")
    protected def preIncrement: Unit = if (isEnd) throw new UnsupportedOperationException("increment on end traverser")

    @conversion @aliasOf("!isEnd")
    final def toBoolean: Boolean = !isEnd

    @conversion
    final def toSIterator: Iterator[A] = ToSIterator(this)

    /**
     * Advances <code>n</code>.
     */
    final def advance(n: Int): Unit = {
        var i = n
        while (i != 0 && !isEnd) {
            increment
            i -= 1
        }
    }

    /**
     * Advances while <code>p</code> meets.
     */
    final def advanceWhile(p: A => Boolean): Unit = {
        while (!isEnd && p(deref)) {
            increment
        }
    }

    final def memoize: Traverser[A] = Memoize(this)
/*
    // (Probably efficiently) compiles without:
    final def &&(p: => Boolean): Boolean = if (!isEnd) p else false
    final def ||(p: => Boolean): Boolean = if (!isEnd) true else p
*/
}


object Traverser extends Compatibles {
}
