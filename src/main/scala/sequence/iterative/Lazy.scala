

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterative


private
case class Lazy[+A](_1: eval.Lazy[Iterative[A]]) extends Forwarder[A] {
    override protected def delegate = _1()
}
