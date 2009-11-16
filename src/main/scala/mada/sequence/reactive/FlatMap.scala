

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package reactive


case class FlatMap[A, +B](_1: Reactive[A], _2: A => Reactive[B]) extends Forwarder[B] {
    override protected val delegate = _1.map(_2).flatten
}
