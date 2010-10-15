

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


// BUGBUG

private
case class Branch[A, B >: A](_1: Reactive[A], _2: Reactive[A] => Reactive[B]) extends Reactive[B] {
    override def close = _1.close
    override def foreach(f: B => Unit) = _1.fork{ r => _2(r).foreach(f) }.foreach(f)
}
