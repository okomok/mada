

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.stl


/**
 * Mixin which supports only the <code>update</code> method, whose index argument is ignored.
 */
trait OutputVector[A] extends Vector[A] {
    /**
     * Does something using <code>e</code>.
     */
    def output(e: A): Unit

    /**
     * Throws <code>UnsupportedOperationException</code>.
     */
    override def size = throw new UnsupportedOperationException("OutputVector.size")

    /**
     * Throws <code>UnsupportedOperationException</code>.
     */
    override def apply(i: Int) = throw new UnsupportedOperationException("OutputVector.apply")

    /**
     * @return  <code>output(e)</code>; <code>i</code> is ignored.
     */
    override def update(i: Int, e: A) = output(e)
}
