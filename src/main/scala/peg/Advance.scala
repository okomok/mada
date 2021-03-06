

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package peg


private
case class Advance(_1: Int) extends Peg[Any] {
    assert(_1 >= 0)

    override def parse(v: sequence.Vector[Any], start: Int, end: Int) = {
        val cur = start + _1
        if (cur <= end) {
            cur
        } else {
            FAILURE
        }
    }

    override def width = _1
}
