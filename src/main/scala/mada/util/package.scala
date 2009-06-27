

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object util {


// assertion

    @packageObjectBrokenOverload
    object assert {
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
    }

    @packageObjectBrokenOverload
    object verify {
        /**
         * Alias of <code>apply</code>; <code>cond</code> is evaluated.
         */
        def apply(cond: Boolean) = util.assert(cond)

        /**
         * Alias of <code>apply</code>; <code>cond</code> is evaluated.
         */
        def apply(msg: => Any, cond: Boolean) = util.assert(msg, cond)
    }

    @packageObjectBrokenOverload
    object ensure {
        /**
         * @return  <code>this(expect(e)); e</code>.
         */
        def apply[A](e: A)(expect: A => Boolean): A = { util.assert(expect(e)); e }

        /**
         * @return  <code>this(msg, expect(e)); e</code>.
         */
        def apply[A](msg: => Any, e: A)(expect: A => Boolean): A = { util.assert(msg, expect(e)); e }
    }


// language

    @returnThat
    def as[A](from: A): A = from

    /**
     * @return  <code>!pre || post</code>.
     */
    def implies(pre: Boolean, post: => Boolean): Boolean = !pre || post

    /**
     * Typed <code>null</code>
     */
    def nullOf[A >: Null]: A = null

    /**
     * @return  <code>null.asInstanceOf[A]</code>.
     */
    def nullInstance[A]: A = null.asInstanceOf[A]


// evaluation strategy

    /**
     * A function calculating <code>body</code> by <code>name</code>.
     */
    def byName[R](body: => R): ByName[R] = ByName{ () => body }

    /**
     * A function calculating <code>body</code> by <code>lazy</code>.
     */
    def byLazy[R](body: => R): ByLazy[R] = ByLazy{ () => body }

    /**
     * A function calculating <code>body</code> in (possibly) other threads.
     */
    def future[R](body: => R): Future[R] = Future{ () => body }

    /**
     * A function calculating <code>body</code> in other threads.
     */
    def parallel[R](body: => R): Parallel[R] = Parallel{ () => body }


// hash code

    /**
     * Hash code of <code>Int</code>
     */
    def hashCodeOfInt(x: Int): Int = x

    /**
     * Hash code of <code>Long</code>
     */
    def hashCodeOfLong(x: Long): Int = (x ^ (x >>> 32)).toInt

    /**
     * @return  <code>java.lang.System.idenityHashCode(x)</code>.
     */
    def hashCodeOfRef(x: AnyRef): Int = java.lang.System.identityHashCode(x)


// pipeline

    sealed class ForwardPipe[A](x: A) {
        def |>[B](f: A => B): B = f(x)
    }
    implicit def |>[A](x: A): ForwardPipe[A] = new ForwardPipe(x)

}
