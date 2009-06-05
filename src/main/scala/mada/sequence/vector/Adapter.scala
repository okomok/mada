

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


/**
 * Adapts underlying vector.
 */
trait Adapter[From, To] extends Vector[To] {
    /**
     * Underlying vector, overridden in subclasses.
     */
    protected def underlying: Vector[From]

    /**
     * @return  <code>underlying.start</code>.
     */
    override def start = underlying.start

    /**
     * @return  <code>underlying.end</code>.
     */
    override def end = underlying.end

    /**
     * @return  <code>underlying(i).asInstanceOf[To]</code>.
     */
    override def apply(i: Int): To = underlying(i).asInstanceOf[To]

    /**
     * @return  <code>underlying(i) = e.asInstanceOf[From]</code>.
     */
    override def update(i: Int, e: To): Unit = underlying(i) = e.asInstanceOf[From]

    /**
     * @return  <code>underlying.isDefinedAt(i)</code>.
     */
    override def isDefinedAt(i: Int): Boolean = underlying.isDefinedAt(i)
}
