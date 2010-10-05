

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package vector


private
case class Bounds[A](_1: Vector[A]) extends TransformAdapter[A] {
    override protected val underlying = _1

    override def apply(i: Int) = { inBounds(i); underlying.apply(i) }
    override def update(i: Int, e: A) = { inBounds(i);  underlying(i) = e }
    override def isDefinedAt(i: Int) = underlying.start <= i && i < underlying.end

    override def bounds = this // bounds-bounds fusion

    private def inBounds(i: Int): Unit = {
        if (!isDefinedAt(i)) {
            throw new IndexOutOfBoundsException(i.toString)
        }
    }
}
