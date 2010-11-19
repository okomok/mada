

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Protect[+A](_1: Reactive[A]) extends Reactive[A] {
    // override def close() = _1.close()
    override def forloop(f: A => Unit, k: => Unit) = _1.forloop(f, k)
    override def protect: Reactive[A] = _1.protect // protect.protect fusion
}
