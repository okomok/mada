

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class Unique[+A](_1: Reactive[A]) extends Forwarder[A] {
    override protected val delegate = _1.uniqueBy(function.equal)

    override def unique: Reactive[A] = this // unique-unique fusion
}

case class UniqueBy[A](_1: Reactive[A], _2: (A, A) => Boolean) extends Forwarder[A] {
    override protected val delegate = _1.reducerLeft{ (b, a) => if (_2(b, a)) b else a }
}
