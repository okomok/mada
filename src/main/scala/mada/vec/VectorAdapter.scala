

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


/**
 * Trait to define a vector from underlying vector.
 * Unlike <code>VectorProxy</code>, method calls are not forwarded to underlying vector.
 */
trait VectorAdapter[Z, A] extends Vector[A] {

    /**
     * Underlying vector, overridden by subclasses.
     */
    def * : Vector[Z]

    /**
     * @return  <code>i</code>, possibly overridden by subclasses.
     */
    def mapIndex(i: Int) = i

    /**
     * @return  <code>*.start</code>, possibly overridden by subclasses.
     */
    override def start = *.start

    /**
     * @return  <code>*.end</code>, possibly overridden by subclasses.
     */
    override def end = *.end

    /**
     * @return  <code>*(mapIndex(i)).asInstanceOf[A]</code>, possibly overridden by subclasses.
     */
    override def apply(i: Int): A = *(mapIndex(i)).asInstanceOf[A]

    /**
     * @return  <code>*(mapIndex(i)) = e.asInstanceOf[Z]</code>, possibly overridden by subclasses.
     */
    override def update(i: Int, e: A): Unit = *(mapIndex(i)) = e.asInstanceOf[Z]

    /**
     * @return  <code>*</code>.
     */
    final def base = *
}
