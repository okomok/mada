

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package auto


import annotation.unchecked.uncheckedVariance


object Auto extends Common with Compatibles


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

    /**
     * Turns to a variable one.
     */
    final def asVar[B](implicit pre: Auto[A] => Auto[B]): Auto[Var[B]] = AsVar(pre(this))

}
