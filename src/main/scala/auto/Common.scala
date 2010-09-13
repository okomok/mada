

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package auto


private[auto]
class Common {

    @returnThat
    def use[A](that: Auto[A]): Auto[A] = that

    @equivalentTo("a.foreach(f)")
    def using[A](a: Auto[A])(f: A => Unit): Unit = a.foreach(f)

    /**
     * Returns an auto reference where <code>as</code> is used by <code>e</code>.
    def usedWith[A](as: Seq[Auto[_]], e: A): Auto[A] = UsedWith(as, e)
     */

    /**
     * Ends with evaluating <code>f</code>.
    def endWith(f: => Unit): Auto[Unit] = EndWith(util.byName(f))
     */

    @equivalentTo("scala.Responder.exec")
    def doing(x: => Unit): Boolean = { x; true }

}
