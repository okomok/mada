

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package util


/**
 * Equivalent to <code>scala.Proxy</code> (with slightly different names).
 *
 * @see <a href>http://java.sun.com/javase/6/docs/api/java/util/Collections.html#unmodifiableCollection(java.util.Collection)</a>
 * @see <a href>http://google-collections.googlecode.com/svn/trunk/javadoc/com/google/common/collect/ForwardingObject.html</a>
 */
trait Forwarder {

    /**
     * Returns the backing delegate instance that methods are forwarded to.
     */
    protected def delegate: Any

    /**
     * Returns <code>true</code> iif the specified object is also a forwarder
     * and <code>delegate.equals(that.delegate)</code> returns <code>true</code>.
     */
    override def equals(that: Any): Boolean = that match {
        case that: Forwarder => delegate.equals(that.delegate) // makes `equals` symmetric.
        case _ => false
    }

    @equivalentTo("delegate.hashCode")
    override def hashCode: Int = delegate.hashCode

    @equivalentTo("delegate.toString")
    override def toString: String = delegate.toString

}
