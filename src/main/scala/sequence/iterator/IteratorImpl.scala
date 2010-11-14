

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterator


/**
 * Trivial helper mixin to implement an Iterator.
 */
trait _Iterator[+A] extends Iterator[A] {
    protected def _isEnd: Boolean
    protected def _deref: A
    protected def _increment(): Unit

    final override def isEnd: Boolean = _isEnd

    final override def deref: A = {
        if (isEnd) {
            throw new NoSuchElementException("deref on end iterator")
        }
        _deref
    }

    final override def increment() {
        if (isEnd) {
            throw new UnsupportedOperationException("increment on end iterator")
        }
        _increment()
    }
}
