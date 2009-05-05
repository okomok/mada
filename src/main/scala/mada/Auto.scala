

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import auto._


/**
 * Contains utility methods operating on <code>Auto</code>.
 */
object Auto extends Eligibles {
    /**
     * Alias of <code>using</code>
     */
    def apply[A, B](e: A)(f: A => B)(implicit a: Auto[A]): B = using(e)(f)(a)

    /**
     * @return  <code>a.begin(e); try { f(e) } finally { a.end(e) }</code>.
     */
    def using[A, B](e: A)(f: A => B)(implicit a: Auto[A]): B = {
        a.begin(e)
        try {
            f(e)
        } finally {
            a.end(e)
        }
    }

    /**
     * Can be used instead of implicit objects.
     */
    trait Interface {
        def begin: Unit = ()
        def end: Unit = ()
    }

    /**
     * Alias of <code>Auto</code>
     */
    type Type[-A] = Auto[A]
}


/**
 * Trait for automatically called methods
 */
trait Auto[-A] extends Companion[Auto.type] {

    override def companion = Auto

    /**
     * Called when block begins.
     */
    def begin(e: A): Unit = ()

    /**
     * Called when block ends.
     */
    def end(e: A): Unit = ()
}
