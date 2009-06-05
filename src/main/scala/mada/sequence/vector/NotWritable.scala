

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


/**
 * Trivial mixin to define a non-writable vector type.
 * Note that it is not effective to mixin Forwarder with this.
 */
trait NotWritable[A] extends Vector[A] {
    /**
     * Throws <code>NotWritableException</code>.
     */
    override def update(i: Int, e: A): Unit = throw NotWritableException(this)

    override def readOnly = this
}
