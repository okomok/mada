

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package peg


case class ReadMap[Z, A](_1: Peg[A], _2: sequence.Vector[Z] => sequence.Vector[A]) extends Peg[Z] {
    override def parse(v: sequence.Vector[Z], start: Int, end: Int) = {
        _1.parse(_2(v), start, end) // _2 must return one-to-one view of sequence.Vector
    }

    override def width = _1.width
}
