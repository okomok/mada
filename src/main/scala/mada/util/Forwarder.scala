

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.util


/**
 * Forwards all calls to methods of class <code>Any</code> to another object <code>delegate</code>.
 * All the forwarding methods are placed in subclasses. For a forwarder to keep <code>equals</code> symmetric,
 * <code>equals</code>(<code>hashCode</code> too) shall not be overriden for purposes other than optimization.
 * In this regard, JCL hierarchy is broken. E.g. <code>unmodifiableCollection</code> can't forward <code>equals</code>.
 * Probably <code>List/Set</code> should have not been a subclass of <code>Collection</code>.
 *
 * @see <a href>http://java.sun.com/javase/6/docs/api/java/util/Collections.html#unmodifiableCollection(java.util.Collection)</a>
 * @see <a href>http://google-collections.googlecode.com/svn/trunk/javadoc/com/google/common/collect/ForwardingObject.html</a>
 */
trait Forwarder {

    /**
     * Returns the backing delegate instance that methods are forwarded to.
     */
    protected def delegate: Any

}
