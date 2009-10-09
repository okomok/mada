

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package peg


case class Named[A](_1: Peg[A], _2: String) extends Forwarder[A] {
    override protected val delegate = _1
    override def toString = _2
}
