

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada


package object util {


// language

    @aliasOf("()")
    val theUnit: Unit = ()

    @equivalentTo("()")
    def ignore(x: Any): Unit = ()

    @equivalentTo("!pre || post")
    def implies(pre: Boolean, post: => Boolean): Boolean = !pre || post

    @equivalentTo("null.asInstanceOf[A]")
    def nullInstance[A]: A = null.asInstanceOf[A]


// evaluation strategy

    /**
     * A function calculating <code>body</code> by <code>name</code>.
     */
    def byName[R](body: => R): ByName[R] = ByName(() => body)

    /**
     * A function calculating <code>body</code> by <code>lazy</code>.
     */
    def byLazy[R](body: => R): ByLazy[R] = ByLazy(() => body)

    /**
     * A function calculating <code>body</code> in possibly other threads.
     */
    def future[R](body: => R): Future[R] = Future(() => body)

    /**
     * A function calculating <code>body</code> in other threads.
     */
    def parallel[R](body: => R): Parallel[R] = Parallel(() => body)


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


// misc

    /**
     * Typed <code>None</code>
     */
    def NoneOf[A]: Option[A] = None

    /**
     * Evaluates <code>body</code> infinite times.
     */
    def repeat(body: => Unit): Unit = while (true) body

    /**
     * Evaluates <code>body</code> <code>n</code> times sequentially.
     */
    def times(n: Int)(body: => Unit): Unit = {
        var i = 0
        while (i != n) {
            body
            i += 1
        }
    }

    /**
     * Evaluates <code>body</code> <code>n</code> times in possibly parallel.
     */
    def timesParallel(n: Int)(body: => Unit): Unit = {
        sequence.vector.range(0, n).parallel.each(_ => body)
    }

}
