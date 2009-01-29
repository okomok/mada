

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
     * @return  <code>i</code>, possibly overridden in subclasses.
     */
    def mapIndex(i: Int) = i

    /**
     * @return  <code>underlying.start</code>, possibly overridden in subclasses.
     */
    override def start = underlying.start

    /**
     * @return  <code>underlying.end</code>, possibly overridden in subclasses.
     */
    override def end = underlying.end

    /**
     * @return  <code>underlying(mapIndex(i)).asInstanceOf[A]</code>, possibly overridden in subclasses.
     */
    override def apply(i: Int): A = underlying(mapIndex(i)).asInstanceOf[A]

    /**
     * @return  <code>underlying(mapIndex(i)) = e.asInstanceOf[Z]</code>, possibly overridden in subclasses.
     */
    override def update(i: Int, e: A): Unit = underlying(mapIndex(i)) = e.asInstanceOf[Z]
}
