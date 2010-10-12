

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package peg


private
case class Call(_1: util.ByName[Unit]) extends Forwarder[Any] {
    override protected val delegate = eps act { _ => _1() }
}
