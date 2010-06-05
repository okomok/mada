

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada


import auto._


/**
 * Trait for "automatic reference"
 */
trait Auto[+A] {

    /**
     * Returns the associated reference.
     */
    def get: A

    /**
     * Called when block begins.
     */
    def begin: Unit = ()

    /**
     * Called when block ends.
     */
    def end: Unit = ()

    @equivalentTo("begin; try { f(get) } finally { end }")
    def usedBy[B](f: A => B): B = {
        begin
        try {
            f(get)
        } finally {
            end
        }
    }

    @aliasOf("usedBy")
    final def foreach[B](f: A => B): B = usedBy(f)

    /**
     * Triggers begin/end iif <code>p</code> satisfies.
     */
    def filter(p: A => Boolean): Auto[A] = Filter(this, p)

    @aliasOf("filter")
    final def withFilter(p: A => Boolean): Auto[A] = filter(p)

}


object Auto {

// compatibles
    implicit def _fromJCloseable[A <: java.io.Closeable](from: A): Auto[A] = fromJCloseable(from)
    implicit def _fromJLock[A <: java.util.concurrent.locks.Lock](from: A): Auto[A] = fromJLock(from)

}
