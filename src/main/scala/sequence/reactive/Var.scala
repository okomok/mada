

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


/**
 * A sequence of variables
 */
final class Var[A](private var x: Option[A] = None) extends Reactive[A] {
    def this(x: A) = this(Some(x))
    def head: A = x.get

    @volatile private var k: A => Unit = null
    override def foreach(f: A => Unit) = {
        if (!x.isEmpty) {
            f(x.get)
        }
        k = f
    }

    /**
     * Assigns `e`.
     */
    def :=(e: A): Unit = {
        if (k == null) {
            x = Some(e)
        } else {
            k(e)
        }
    }
}
