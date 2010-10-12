

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package peg


private
case class Lookaround[A](_1: sequence.vector.Pred[A]) extends Forwarder[A] {
    override protected val delegate = lookaround3(sequence.vector.triplify(_1))
}

private
case class Lookaround3[A](_1: sequence.vector.Pred3[A]) extends Peg[A] with ZeroWidth[A] {
    override def parse(v: sequence.Vector[A], start: Int, end: Int) = {
        if (_1(v, start, end)) {
            start
        } else {
            FAILURE
        }
    }
}


private
case class Begin() extends Forwarder[Any] {
    override protected val delegate = lookaround3[Any]{ (v, i, _) => i == v.start }
}

private
case class End() extends Forwarder[Any] {
    override protected val delegate = lookaround3[Any]{ (v, i, _) => i == v.end }
}


private
case class Eps() extends Forwarder[Any] {
    override protected val delegate = lookaround3[Any]{ (_, _, _) => true }
}


private
case class Fail() extends Forwarder[Any] {
    override protected val delegate = lookaround3[Any]{ (_, _, _) => false }
}
