

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package peg


private[mada] case class Switch[A](_1: scala.collection.Map[A, Peg[A]]) extends Peg[A] {
    override def parse(v: sequence.Vector[A], start: Int, end: Int) = {
        if (start == end) {
            FAILURE
        } else {
            val p = _1.get(v(start))
            if (p.isEmpty) {
                FAILURE
            } else {
                p.get.parse(v, start + 1, end)
            }
        }
    }
}
