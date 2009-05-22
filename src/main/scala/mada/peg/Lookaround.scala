

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


case class Lookaround[A](_1: vector.Pred[A]) extends Forwarder[A] {
    override protected val delegate = lookaround3(vector.triplify(_1))
}

case class Lookaround3[A](_1: vector.Pred3[A]) extends Peg[A] with ZeroWidth[A] {
    override def parse(v: Vector[A], start: Int, end: Int) = {
        if (_1(v, start, end)) {
            start
        } else {
            FAILURE
        }
    }
}


case class Begin[A]() extends Forwarder[A] {
    override protected val delegate = lookaround3[A]{ (v, i, _) => i == v.start }
}

case class End[A]() extends Forwarder[A] {
    override protected val delegate = lookaround3[A]{ (v, i, _) => i == v.end }
}


case class Eps[A]() extends Forwarder[A] {
    override protected val delegate = lookaround3[A]{ (_, _, _) => true }
}


case class Fail[A]() extends Forwarder[A] {
    override protected val delegate = lookaround3[A]{ (_, _, _) => false }
}
