

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package peg


private[mada] case class Lookaround[A](_1: sequence.vector.Pred[A]) extends Forwarder[A] {
    override protected val delegate = lookaround3(sequence.vector.triplify(_1))
}

private[mada] case class Lookaround3[A](_1: sequence.vector.Pred3[A]) extends Peg[A] with ZeroWidth[A] {
    override def parse(v: sequence.Vector[A], start: Int, end: Int) = {
        if (_1(v, start, end)) {
            start
        } else {
            FAILURE
        }
    }
}


private[mada] case class Begin[A]() extends Forwarder[A] {
    override protected val delegate = lookaround3[A]{ (v, i, _) => i == v.start }
}

private[mada] case class End[A]() extends Forwarder[A] {
    override protected val delegate = lookaround3[A]{ (v, i, _) => i == v.end }
}


private[mada] case class Eps[A]() extends Forwarder[A] {
    override protected val delegate = lookaround3[A]{ (_, _, _) => true }
}


private[mada] case class Fail[A]() extends Forwarder[A] {
    override protected val delegate = lookaround3[A]{ (_, _, _) => false }
}
