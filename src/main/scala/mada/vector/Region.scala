

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vector


// Equivalent to iterator_range.

/**
 * Replaces <code>start</code> and <code>end</code> of <code>underlying</code> vector.
 * Note that a larger vector than <code>underlying</code> is ALLOWED as far as <code>isDefinedAt</code> says ok.
 *
 * @pre <code>start <= end</code>
 */
case class Region[A](override val underlying: Vector[A], override val start: Int, override val end: Int) extends Adapter.Transform[A] {
    if (start > end) {
        throw new IllegalArgumentException("Region" + (start, end))
    }

    /**
     * Rewrites region of region into flat region.
     * <code>vector.seal</code> can work around this rewriting.
     */
    override def region(_start: Int, _end: Int) = { // region-region fusion
        if (_start == start && _end == end) {
            this
        } else {
            underlying.region(_start, _end)
        }
    }

    /**
     * @return  <code>underlying</code>.
     */
    override def regionBase = underlying
}
