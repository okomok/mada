

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package auto


object Auto extends Common with Compatibles {

// methodization
    sealed class _OfInvariant[A](_this: Auto[A]) {
        def asVar: Auto[Var[A]] = AsVar(_this)
    }
    implicit def _ofInvariant[A](_this: Auto[A]): _OfInvariant[A] = new _OfInvariant(_this)

}


/**
 * Trait for "automatic reference"
 */
trait Auto[+A] {

    @returnThis
    final def asAuto: Auto[A] = this

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

}
