

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package auto {

    trait Eligibles {
        implicit object ofInterface extends Auto[Auto.Interface] {
            def dispose(e: Auto.Interface) = e.dispose
        }

        implicit object ofCloseable extends Auto[java.io.Closeable] {
            def dispose(e: java.io.Closeable) = e.close
        }
    }

} // package auto


/**
 * Contains utility methods operating on <code>Auto</code>.
 */
object Auto extends auto.Eligibles {
    /**
     * Alias of <code>using</code>
     */
    def apply[A, B](e: A)(f: A => B)(implicit a: Auto[A]): B = using(e)(f)(a)

    /**
     * @return  <code>try { f(e) } finally { a.dispose(e) }</code>.
     */
    def using[A, B](e: A)(f: A => B)(implicit a: Auto[A]): B = try { f(e) } finally { a.dispose(e) }

    /**
     * Can be used instead of implicit objects.
     */
    trait Interface {
        def dispose: Unit
    }

    /**
     * Alias of <code>Auto</code>
     */
    type Type[-A] = Auto[A]
}


/**
 * Trait for automatically called methods
 */
trait Auto[-A] {
    /**
     * Cleans up resources.
     */
    def dispose(e: A): Unit

    /**
     * Alias of <code>Auto</code>
     */
    final def companion = Auto
}