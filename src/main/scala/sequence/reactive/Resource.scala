

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


/**
 * Mixin for a Reactive resource.
 */
trait Resource[+A] extends ReactiveOnce[A] {
    protected def openResource(f: A => Unit, k: => Unit): Unit
    protected def closeResource(): Unit

    final override def onceOnly(f: A => Unit, k: => Unit) = openResource(f, k)
    final override def close() = c
    private[this] lazy val c = closeResource()
}

/**
 * Mixin for a Reactive resource.
 */
trait NoEndResource[+A] extends ReactiveOnce[A] {
    protected def openResource(f: A => Unit): Unit
    protected def closeResource(): Unit

    final override def onceOnly(f: A => Unit, k: => Unit) = openResource(f)
    final override def close() = c
    private[this] lazy val c = closeResource()
}
