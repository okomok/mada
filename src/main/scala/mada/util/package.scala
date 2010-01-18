

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object util {


// assertion

    /**
     * The assert
     */
    def assert(cond: => Boolean): Unit = {
        blend.doIf[isDebug] {
            if (!cond) {
                throw new java.lang.AssertionError("assertion failed")
            }
        }
    }

    /**
     * assert with message
     */
    def assert(msg: => Any, cond: => Boolean): Unit = {
        blend.doIf[isDebug] {
            if (!cond) {
                throw new java.lang.AssertionError("assertion failed: " + msg)
            }
        }
    }

    /**
     * Alias of <code>apply</code>; <code>cond</code> is evaluated.
     */
    def verify(cond: Boolean) = util.assert(cond)

    /**
     * Alias of <code>apply</code>; <code>cond</code> is evaluated.
     */
    def verify(msg: => Any, cond: Boolean) = util.assert(msg, cond)

    @equivalentTo("this(expect(e)); e")
    def ensure[A](e: A)(expect: A => Boolean): A = { util.assert(expect(e)); e }

    @equivalentTo("this(msg, expect(e)); e")
    def ensure[A](msg: => Any, e: A)(expect: A => Boolean): A = { util.assert(msg, expect(e)); e }


// language

    @aliasOf("()")
    val theUnit: Unit = ()

    @equivalentTo("()")
    def ignore(x: Any): Unit = ()

    @equivalentTo("!pre || post")
    def implies(pre: Boolean, post: => Boolean): Boolean = !pre || post

    /**
     * Typed <code>null</code>
     */
    def nullOf[A >: Null]: A = null

    @equivalentTo("null.asInstanceOf[A]")
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

    @equivalentTo("java.lang.System.idenityHashCode(x)")
    def hashCodeOfRef(x: AnyRef): Int = java.lang.System.identityHashCode(x)


// pipeline

    sealed class ForwardPipe[A](x: A) {
        def |>[B](f: A => B): B = f(x)
    }
    implicit def |>[A](x: A): ForwardPipe[A] = new ForwardPipe(x)

}
