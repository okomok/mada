

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


// Compiler bug? imports should have higher precedence than package members.
// import ptr._


/**
 * Contains utility methods operating on <code>Pointer</code>.
 */
private[mada] object Pointer extends ptr.Conversions with ptr.Compatibles {
    /**
     * The end pointer
     */
    val end: Pointer[Nothing] = new Pointer[Nothing] {
        override def isEnd: Boolean = true
        override def deref = throw new NoSuchElementException("deref on the end pointer")
        override def increment = throw new UnsupportedOperationException("increment on the end pointer")
    }

    /**
     * @return  <code>end</code>.
     */
    def endOf[A]: Pointer[A] = end
}


/**
 * Equivalent to <code>Iterator</code>, but useful if <code>Iterator</code>
 * is cumbersome for implementing complicated algorithms.
 *
 * @see     scalax.BufferedIterator
 */
private[mada] trait Pointer[+A] {
    /**
     * Alias of <code>isEnd</code>
     */
    final def unary_! : Boolean = isEnd

    /**
     * Alias of <code>deref</code>
     */
    final def unary_~ : A = deref

    /**
     * Alias of <code>increment</code>
     */
    final def ++ : Unit = increment

    /**
     * Is pointer pass-the-end?
     */
    def isEnd : Boolean

    /**
     * Dereferences pointer.
     */
    def deref: A

    /**
     * Increments pointer.
     */
    def increment : Unit
}
