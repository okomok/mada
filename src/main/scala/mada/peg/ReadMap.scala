

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


case class ReadMap[Z, A](_1: Peg[A], _2: Vector[Z] => Vector[A]) extends Peg[Z] {
    override def parse(v: Vector[Z], start: Int, end: Int) = {
        _1.parse(_2(v), start, end) // _2 must return one-to-one view of Vector
    }

    override def width = _1.width
}
