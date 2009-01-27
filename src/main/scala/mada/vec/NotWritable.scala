

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


/**
 * Trivial mixin to define a non-writable vector type.
 */
trait NotWritable[A] extends Vector[A] {
    /**
     * Throws <code>NotWritableException</code>.
     */
    override def update(i: Int, e: A): Unit = throw new Vector.NotWritableException(this)

    override def readOnly = this
}
