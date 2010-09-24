

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


/**
 * A sequence of variables, considered as a lightweight reactive.ArrayList.
 * This can hold only one listener. You can't place this in nested
 * position of for-expression if outer sequence has multiple elements.
 */
final class Var[A](private var x: Option[A] = None) extends Reactive[A] {
    def this(x: A) = this(Some(x))

    @volatile private var out: A => Unit = null

    override def foreach(f: A => Unit) = {
        if (!x.isEmpty) f(x.get)
        out = f
    }
    override def head: A = x.getOrElse(super.head)

    def :=(y: A): Unit = {
        x = Some(y)
        out(y)
    }
}


object Var {
    @equivalentTo("new Var(x)")
    def apply[A](x: A): Var[A] = new Var(x)
}