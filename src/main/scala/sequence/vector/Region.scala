

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


case class Region[A](_1: Vector[A], _2: Int, _3: Int) extends TransformAdapter[A] {
    Precondition.range(_2, _3, "Region")

    override protected val underlying = _1
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

    @equivalentTo("underlying")
    override def regionBase = underlying
}


private
case class Take[A](_1: Vector[A], _2: Int) extends Forwarder[A] {
    override protected val delegate = _1(_1.start, java.lang.Math.min(_1.start + _2, _1.end))
}

private
case class Drop[A](_1: Vector[A], _2: Int) extends Forwarder[A] {
    override protected val delegate = _1(java.lang.Math.min(_1.start + _2, _1.end), _1.end)
}

private
case class Slice[A](_1: Vector[A], _2: Int, _3: Int) extends Forwarder[A] {
    override protected val delegate = _1.drop(_2).take(_3 - _2)
}


private
case class Init[A](_1: Vector[A]) extends Forwarder[A] {
    Precondition.notEmpty(_1, "init")
    override protected val delegate = _1(_1.start, _1.end - 1)
}

private
case class Clear[A](_1: Vector[A]) extends Forwarder[A] {
    override protected val delegate = _1(_1.start, _1.start)
}

private
case class Window[A](_1: Vector[A], _2: Int, _3: Int) extends Forwarder[A] {
    override protected val delegate = _1(_1.start + _2, _1.start + _3)
}

private
case class Offset[A](_1: Vector[A], _2: Int, _3: Int) extends Forwarder[A] {
    override protected val delegate = _1(_1.start + _2, _1.end + _3)
}
