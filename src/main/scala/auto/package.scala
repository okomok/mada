

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada


package object auto {


    @aliasOf("Auto")
    type Type[+A] = Auto[A]


// constructor

    @returnThat
    def use[A](that: Auto[A]): Auto[A] = that

    @equivalentTo("a.usedBy(f)")
    def using[A, B](a: Auto[A])(f: A => B): B = a.usedBy(f)

    /**
     * Returns an auto reference where <code>as</code> is used by <code>e</code>.
     */
    def usedWith[A](as: Seq[Auto[_]], e: A): Auto[A] = UsedWith(as, e)

    /**
     * Ends with evaluating <code>f</code>.
     */
    def endWith(f: => Unit): Auto[Unit] = EndWith(util.byName(f))

    /**
     * Uses a variable Auto.
     */
    def useVar[A](a: Auto[A]): Auto[Var[A]] = UseVar(a)

    @equivalentTo("scala.Responder.exec")
    def doing(x: => Unit): Boolean = { x; true }


// conversion

    @compatibleConversion
    def fromJCloseable[A <: java.io.Closeable](from: A): Auto[A] = FromJCloseable(from)

    @compatibleConversion
    def fromJLock[A <: java.util.concurrent.locks.Lock](from: A): Auto[A] = FromJLock(from)

}
