

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


/**
 * Mixin for a sequence which doesn't allow re-foreach.
 */
trait ReactiveOnce[+A] extends Reactive[A] {
    protected def foreachOnce(f: A => Unit): Unit

    private val k =
        IfFirst[A => Unit] { f =>
            foreachOnce(f)
        } Else { _ =>
            throw ReactiveOnceException(this)
        }

    final override def foreach(f: A => Unit) = k(f)
}

case class ReactiveOnceException[A](_1: Reactive[A]) extends
    RuntimeException("multiple `foreach` calls not allowed")
