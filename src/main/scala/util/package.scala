

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


// type constraints

    /**
     * Returns true iif implicit value found.
     */
    def hasImplicit[A](implicit _hasImplicit: HasImplicit[A] = HasImplicit._ofNotFound) = _hasImplicit()

    /**
     * Returns true iif type <code>A</code> is the same as type <code>B</code>.
     */
    def isSame[A, B](implicit c: A =:= B = null): Boolean = null ne c

    /**
     * Returns true iif type <code>A</code> conforms type <code>B</code>.
     */
    def conforms[A, B](implicit c: A <:< B = null): Boolean = null ne c

    /**
     * Returns true iif type <code>A</code> is compatible to type <code>B</code>.
     */
    def compatible[A, B](implicit c: A <%< B = null): Boolean = null ne c


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

    sealed class BackwardPipe[A](x: A) {
        def |<(f: A => Any): A = { f(x); x }
    }
    implicit def |<[A](x: A): BackwardPipe[A] = new BackwardPipe(x)


// misc

    /**
     * Typed <code>None</code>
     */
    def NoneOf[A]: Option[A] = None

    /**
     * Returns an Option value instead of exception.
     */
    def optional[R](body: => R): Option[R] = try {
        Some(body)
    } catch {
        case _ => None
    }

    /**
     * Evaluates <code>body</code> infinite times.
     */
    def repeat(body: => Unit): Unit = while (true) body

    /**
     * Evaluates <code>body</code> <code>n</code> times sequentially.
     */
    def times(n: Int)(body: => Unit) {
        var i = 0
        while (i != n) {
            body
            i += 1
        }
    }

    /**
     * Evaluates <code>body</code> <code>n</code> times in possibly parallel.
     */
    def timesParallel(n: Int)(body: => Unit) {
        sequence.vector.range(0, n).parallel.pareach(_ => body)
    }

    /**
     * Evaluates <code>body</code> in the event-dispatch-thread.
     */
    def inEdt[R](body: => R): R = {
        import javax.swing.SwingUtilities
        if (SwingUtilities.isEventDispatchThread) {
            body
        } else {
            var r: Option[R] = None
            try {
                SwingUtilities.invokeAndWait {
                    new Runnable {
                        override def run {
                            r = Some(body)
                        }
                    }
                }
            } catch {
                case e: java.lang.reflect.InvocationTargetException => throw e.getCause
            }
            r.get
        }
    }

}
