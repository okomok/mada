

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


/**
 * A sequence of variables
 */
final class Var[A](private var x: Option[A] = None) extends Reactive[A] with Function1[A, Unit] {
    def this(x: A) = this(Some(x))

    override def head: A = if (x.isEmpty) super.head else x.get

    @volatile private var out: A => Unit = null
    override def foreach(f: A => Unit) = {
        if (!x.isEmpty) {
            f(x.get)
        }
        out = f
    }

    /**
     * Assigns `e`.
     */
    def :=(e: A): Unit = out(e)

    @equivalentTo(":=(e)")
    override def apply(e: A): Unit = this := e
}
