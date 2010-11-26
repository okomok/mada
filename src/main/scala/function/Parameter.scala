

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package function


/**
 * Represents named parameter.
 */
trait Parameter[A] { self =>

    /**
     * Returns <code>this</code> as parameter's unique id.
     */
    def origin: Parameter[A] = this

    /**
     * The argument passed to this parameter
     */
    def argument: A = throw new IllegalArgumentException("missing argument: " + this)

    @annotation.aliasOf("pass")
    final def ->(v: A): Parameter[A] = pass(v)

    /**
     * Passes the argument to this parameter.
     */
    final def pass(v: A): Parameter[A] = new Parameter[A] {
        override def origin = self
        override def argument = v
    }

}
