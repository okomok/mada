

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Assertion methods used in Mada
 */
object Assert {
    /**
     * Is assertion enabled?
     */
    final val isEnabled = true
    // var isEnabled = true

    /**
     * The assert
     */
    def apply(cond: => Boolean): Unit = { // Without Yclosure-elim, by-name parameters require heap allocation.
        // For some reason, optimizer dislikes ! and && operators.
        if (isEnabled) {
            if (!cond) {
                throw new java.lang.AssertionError("assertion failed")
            }
        }
    }

    /**
     * assert with message
     */
    def apply(msg: => Any, cond: => Boolean): Unit = {
        if (isEnabled) {
            if (!cond) {
                throw new java.lang.AssertionError("assertion failed: " + msg)
            }
        }
    }

    /**
     * Alias of this.apply; <code>cond</code> is evaluated.
     */
    def verify(cond: Boolean) = Assert(cond)

    /**
     * Alias of this.apply; <code>cond</code> is evaluated.
     */
    def verify(msg: => Any, cond: Boolean) = Assert(msg, cond)


    /**
     * @return  <code>this(expect(e)); e</code>
     */
    def check[A](expect: A => Boolean, e: A): A = { Assert(expect(e)); e }

    /**
     * @return  <code>this(msg, expect(e); e</code>
     */
    def check[A](msg: => Any, expect: A => Boolean, e: A): A = { Assert(msg, expect(e)); e }
}
