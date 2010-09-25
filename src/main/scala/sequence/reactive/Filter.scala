

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Filter[A](_1: Reactive[A], _2: A => Boolean) extends TransformAdapter[A] {
    override def underlying = _1
    override def foreach(f: A => Unit) = for (x <- _1) { if (_2(x)) f(x) }
}

private
case class Remove[A](_1: Reactive[A], _2: A => Boolean) extends Forwarder[A] {
    override protected val delegate = _1.filter(function.not(_2))
}
