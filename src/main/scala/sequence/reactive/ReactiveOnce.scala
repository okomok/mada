

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


case class ReactiveOnceException[A](_1: Reactive[A]) extends
    UnsupportedOperationException("multiple `foreach` calls not allowed")

/**
 * Mixin for a sequence which doesn't allow re-foreach.
 */
trait ReactiveOnce[+A] extends Reactive[A] {
    private var isFirst = true
    protected def foreachOnce(f: A => Unit): Unit
    final override def foreach(f: A => Unit) = {
        if (isFirst) {
            isFirst = false
            foreachOnce(f)
        } else {
            throw ReactiveOnceException(this)
        }
    }
}
