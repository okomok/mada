

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package auto


private[auto]
class Common {

    @returnThat
    def use[A](that: Auto[A]): Auto[A] = that

    @equivalentTo("a.foreach(f)")
    def using[A](a: Auto[A])(f: A => Unit): Unit = a.foreach(f)

    /**
     * The empty Auto
     */
    val empty: Auto[Nothing] = Empty()

    /**
     * A const Auto
     */
    def const(f: => Unit): Auto[Nothing] = Const(util.byLazy(f))

    /**
     * Returns an Auto containing one element.
     */
    def single[A](e: A): Auto[A] = Single(e)

    /*
    @equivalentTo("scala.Responder.exec")
    def doing(x: => Unit): Boolean = { x; true }
    */
}
