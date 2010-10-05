

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package peg


private
case class Longest[A](_1: sequence.Iterative[Peg[A]]) extends Forwarder[A] {
    override protected val delegate: Peg[A] = new Best(_1, java.lang.Math.max)
}

private
case class Shortest[A](_1: sequence.Iterative[Peg[A]]) extends Forwarder[A] {
    override protected val delegate: Peg[A] = new Best(_1, java.lang.Math.min)
}


private class Best[A](_1: sequence.Iterative[Peg[A]], _2: (Int, Int) => Int) extends Peg[A] {
    override def parse(v: sequence.Vector[A], start: Int, end: Int) = {
        val curs = _1.map{ p => p.parse(v, start, end) }.filter{ i => i != FAILURE }
        if (curs.isEmpty) FAILURE else curs.reduceLeft(_2)
    }
}
