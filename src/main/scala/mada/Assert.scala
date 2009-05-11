

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Assertion methods used in Mada
 */
object Assert {
    /**
     * The assert
     */
    def apply(cond: => Boolean): Unit = {
        blend.doIf[isDebug] {
            if (!cond) {
                throw new java.lang.AssertionError("assertion failed")
            }
        }
    }

    /**
     * assert with message
     */
    def apply(msg: => Any, cond: => Boolean): Unit = {
        blend.doIf[isDebug] {
            if (!cond) {
                throw new java.lang.AssertionError("assertion failed: " + msg)
            }
        }
    }

    /**
     * Alias of <code>apply</code>; <code>cond</code> is evaluated.
     */
    def verify(cond: Boolean) = Assert(cond)

    /**
     * Alias of <code>apply</code>; <code>cond</code> is evaluated.
     */
    def verify(msg: => Any, cond: Boolean) = Assert(msg, cond)

    /**
     * @return  <code>this(expect(e)); e</code>.
     */
    def ensure[A](e: A)(expect: A => Boolean): A = { Assert(expect(e)); e }

    /**
     * @return  <code>this(msg, expect(e)); e</code>.
     */
    def ensure[A](msg: => Any, e: A)(expect: A => Boolean): A = { Assert(msg, expect(e)); e }
}
