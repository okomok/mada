

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class Region[A](_1: Vector[A], _2: Int, _3: Int) extends Adapter.Transform[A] {
    if (_2 > _3) {
        throw new IllegalArgumentException("Region" + (_2, _3))
    }

    override val underlying = _1
    override val start = _2
    override val end = _3

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


case class Init[A](_1: Vector[A]) extends Forwarder[A] {
    ThrowIf.empty(_1, "init")
    override protected val delegate = _1(_1.start, _1.end - 1)
}

case class Clear[A](_1: Vector[A]) extends Forwarder[A] {
    override protected val delegate = _1(_1.start, _1.start)
}

case class Window[A](_1: Vector[A], _2: Int, _3: Int) extends Forwarder[A] {
    override protected val delegate = _1(_1.start + _2, _1.start + _3)
}

case class Offset[A](_1: Vector[A], _2: Int, _3: Int) extends Forwarder[A] {
    override protected val delegate = _1(_1.start + _2, _1.end + _3)
}
