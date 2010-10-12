

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package peg


private
case class Dot() extends Forwarder[Any] {
    override protected val delegate = advance(1)
}
