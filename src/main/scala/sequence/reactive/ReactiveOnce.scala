

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


import eval.ByName


/**
 * Mixin for a sequence which doesn't allow re-foreach.
 */
trait ReactiveOnce[+A] extends Reactive[A] {
    protected def forloopOnce(f: A => Unit, k: => Unit): Unit

    private[this] val _forloop =
        IfFirst[(A => Unit, ByName[Unit])] { case (f, k) =>
            forloopOnce(f, k)
        } Else { _ =>
            throw ReactiveOnceException(this)
        }

    final override def forloop(f: A => Unit, k: => Unit) = _forloop(f, ByName(k))
}

case class ReactiveOnceException[A](_1: Reactive[A]) extends
    RuntimeException("multiple `foreach` calls not allowed")
