

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package peg


case class Advance[A](_1: Int) extends Peg[A] {
    util.assert(_1 >= 0)

    override def parse(v: sequence.Vector[A], start: Int, end: Int) = {
        val cur = start + _1
        if (cur <= end) {
            cur
        } else {
            FAILURE
        }
    }

    override def width = _1
}
