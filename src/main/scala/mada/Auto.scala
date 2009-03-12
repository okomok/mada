

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package auto {

    trait Eligibles {
        import Auto.Closeable

        implicit object fromCloseable extends Auto[Closeable] {
            def dispose(e: Closeable) = e.close
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
    def apply[A](e: A)(f: A => Unit)(implicit a: Auto[A]): Unit = using(e)(f)(a)

    /**
     * @return  <code>try { f(e) } finally { a.dispose(e) }</code>.
     */
    def using[A](e: A)(f: A => Unit)(implicit a: Auto[A]): Unit = try { f(e) } finally { a.dispose(e) }

    /**
     * Alias of <code>java.io.Closeable</code>
     */
    type Closeable = java.io.Closeable
}


/**
 * Trait for automatically called methods
 */
trait Auto[-A] {
    /**
     * Cleans up resources.
     */
    def dispose(e: A): Unit
}
