

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package util


/**
 * Equivalent to <code>scala.Proxy</code> (with slightly different names).
 */
trait Forwarder {

    /**
     * Returns the backing delegate instance that methods are forwarded to.
     */
    protected def delegate: Any

    override def equals(that: Any): Boolean = {
        if (that == null) {
            false
        } else {
            that equals delegate // for the symmetry.
        }
    }

    @annotation.equivalentTo("delegate.hashCode")
    override def hashCode: Int = delegate.hashCode

    @annotation.equivalentTo("delegate.toString")
    override def toString: String = delegate.toString

}
