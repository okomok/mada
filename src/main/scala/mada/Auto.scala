

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on <code>Auto</code>.
 */
object Auto {
    /**
     * Alias of <code>using</code>
     */
    def apply[A](a: A)(f: A => Unit)(implicit c: A => Auto): Unit = using(a)(f)

    /**
     * @return  <code>try { f(a) } finally { a.dispose }</code>.
     */
    def using[A](a: A)(f: A => Unit)(implicit c: A => Auto): Unit = try { f(a) } finally { c(a).dispose }
}


/**
 * Trait for automatic clean up
 */
trait Auto {
    /**
     * Cleans up resources.
     */
    def dispose: Unit
}
