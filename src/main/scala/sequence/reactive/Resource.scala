

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


/**
 * Mixin for a Reactive resource.
 */
trait Resource[+A] extends ReactiveOnce[A] with java.io.Closeable {
    @equivalentTo("using(this)")
    final def used: Reactive[A] = using(this)

    protected def openResource(f: A => Unit): Unit
    protected def closeResource: Unit

    final override def foreachOnce(f: A => Unit) = openResource(f)
    final override lazy val close = closeResource
}
