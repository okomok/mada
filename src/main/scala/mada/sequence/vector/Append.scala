

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class Append[A](_1: Vector[A], _2: Vector[A]) extends Vector[A] {
    override def start = 0
    override def end = _1.nth.size + _2.nth.size

    override def apply(i: Int) = {
        if (i < _1.nth.size) {
            _1.nth(i)
        } else {
            _2.nth(i - _1.nth.size)
        }
    }
    override def update(i: Int, e: A) = {
        if (i < _1.nth.size) {
            _1.nth(i) = e
        } else {
            _2.nth(i - _1.nth.size) = e
        }
    }
    override def isDefinedAt(i: Int) = {
        if (i < _1.nth.size) {
            _1.nth.isDefinedAt(i)
        } else {
            _2.nth.isDefinedAt(i - _1.nth.size)
        }
    }
}
