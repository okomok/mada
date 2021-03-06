

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class OnClose[+A](_1: Reactive[A], _2: eval.ByName[Unit]) extends Reactive[A] {
    override def close() = { _2(); _1.close() }
    override def forloop(f: A => Unit, k: Exit => Unit) = _1.forloop(f, k)
}
