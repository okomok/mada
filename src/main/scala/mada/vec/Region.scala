

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


/**
 * Replaces <code>start</code> and <code>end</code> of <code>underlying</code> vector.
 * Note that a larger vector than <code>underlying</code> is ALLOWED as far as <code>isDefinedAt</code> says ok.
 *
 * @pre     <code>start <= end</code>
 */
case class Region[A](override val underlying: Vector[A], override val start: Int, override val end: Int) extends VectorAdapter[A, A] {
    override def region(_start: Int, _end: Int) = underlying.region(_start, _end) // region-region fusion
}
