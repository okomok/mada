

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object auto {

    @aliasOf("Auto")
    type Type[+A] = Auto[A]

    @returnThat
    def use[A](that: Auto[A]): Auto[A] = that

    @equivalentTo("a.usedBy(f)")
    @packageObjectBrokenOverload
    def using[A, B](a: Auto[A])(f: A => B): B = a.usedBy(f)

    /**
     * Returns an auto reference where <code>as</code> is used by <code>e</code>.
     */
    def usedWith[A](as: Seq[Auto[_]], e: A): Auto[A] = UsedWith(as, e)

// conversion
    @compatibleConversion
    def fromJCloseable[A <: java.io.Closeable](from: A): Auto[A] = FromJCloseable(from)

    @compatibleConversion
    def fromJLock[A <: java.util.concurrent.locks.Lock](from: A): Auto[A] = FromJLock(from)

}
