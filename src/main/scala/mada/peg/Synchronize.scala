

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package peg


case class Synchronize[A](_1: Peg[A]) extends Peg[A] {
    override def parse(v: sequence.Vector[A], start: Int, end: Int) = synchronized { _1.parse(v, start, end) }

    override def width = synchronized { _1.width }
}
