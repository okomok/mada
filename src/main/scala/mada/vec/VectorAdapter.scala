

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


/**
 * Trait to define a vector from underlying vector.
 * Unlike <code>VectorProxy</code>, method calls are not forwarded to underlying vector.
 */
trait VectorAdapter[Z, A] extends Vector[A] {
    /**
     * Underlying vector, overridden in subclasses.
     */
    def underlying: Vector[Z]

    /**
     * @return  <code>underlying.start</code>.
     */
    override def start = underlying.start

    /**
     * @return  <code>underlying.end</code>.
     */
    override def end = underlying.end

    /**
     * @return  <code>underlying(i).asInstanceOf[A]</code>.
     */
    override def apply(i: Int): A = underlying(i).asInstanceOf[A]

    /**
     * @return  <code>underlying(i) = e.asInstanceOf[Z]</code>.
     */
    override def update(i: Int, e: A): Unit = underlying(i) = e.asInstanceOf[Z]

    /**
     * @return  <code>underlying.isDefinedAt(i)</code>.
     */
    override def isDefinedAt(i: Int): Boolean = underlying.isDefinedAt(i)
}
