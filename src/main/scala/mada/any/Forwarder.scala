

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.any


/**
 * Forwards all calls to methods of class <code>Any</code> to another object <code>delegate</code>.
 * <code>any.Forwarder</code> is nearly a marker trait: actual forwarding is implemented by subclasses.
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
