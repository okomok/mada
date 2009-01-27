

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


/**
 * Trivial mixin to define a non-writable vector type.
 */
trait NotWritable[A] extends Vector[A] {
    /**
     * Throws <code>NotWritableError</code>.
     */
    override def update(i: Int, e: A): Unit = throw new NotWritableError(this)

    override def readOnly = this
}
