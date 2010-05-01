

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package peg


case class Lookahead[A](_1: Peg[A]) extends Peg[A] with ZeroWidth[A] {
    override def parse(v: sequence.Vector[A], start: Int, end: Int) = {
        if (_1.parse(v, start, end) != FAILURE) {
            start
        } else {
            FAILURE
        }
    }
}
