

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


case class ResourceException[A](_1: Reactive[A]) extends
    UnsupportedOperationException("`close` shall be called before the second `foreach` call")

/**
 * Mixin for a Reactive resource.
 */
trait Resource[+A] extends Reactive[A] with java.io.Closeable {
    @equivalentTo("using(this)")
    def used: Reactive[A] = using(this)

    private var _isOpen = false

    protected def openResource(f: A => Unit): Unit
    protected def closeResource: Unit

    final override def foreach(f: A => Unit) = {
        if (_isOpen) {
            throw ResourceException(this)
        } else {
             // `close` may be called in other threads before `openResource` returns.
            _isOpen = true
            try {
                openResource(f) // sure to offer the happens-before guarantee.
            } catch {
                case e => {
                    _isOpen = false
                    throw e
                }
            }
        }
    }

    final override def close = {
        if (_isOpen) {
            closeResource
            _isOpen = false
        }
    }

    final def isOpen: Boolean = _isOpen
}
