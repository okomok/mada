

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterative


private
case class Tail[+A](_1: Iterative[A]) extends Forwarder[A] {
    override protected val delegate = _1.drop(1)
}
