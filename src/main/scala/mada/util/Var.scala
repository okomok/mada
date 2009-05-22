

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.util


/**
 * Mixin to represent mutable forwarder.
 */
trait Var[A <: AnyRef] extends Forwarder {
    override protected def delegate: A

    /**
     * Assigns <code>that</code> to <code>delegate</code>.
     */
    def assign(that: A): Unit

    /**
     * @return  <code>delegate eq null</code>.
     */
    final def isNull: Boolean = delegate eq null

    @aliasOf("assign")
    final def :=(that: A): Unit = assign(that)
}
