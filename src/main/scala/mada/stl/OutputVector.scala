

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
     * @return  <code>Vector.NULL_INDEX</code>.
     */
    final override def start = Vector.NULL_INDEX

    /**
     * @return  <code>Vector.nullIndex</code>.
     */
    final override def end = Vector.NULL_INDEX

    /**
     * Throws <code>UnsupportedOperationException</code>.
     */
    final override def apply(i: Int) = throw new UnsupportedOperationException("OutputVectors.apply")

    /**
     * @return  <code>output(e)</code>; <code>i</code> is ignored.
     */
    final override def update(i: Int, e: A) = output(e)
}
