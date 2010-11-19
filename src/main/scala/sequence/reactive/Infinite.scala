

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


/**
 * Mixin for an infinite sequence
 */
trait Infinite[+A] { this: Reactive[A] =>
    protected def forever(f: A => Unit): Unit
    final override def forloop(f: A => Unit, k: => Unit) = forever(f)
}
