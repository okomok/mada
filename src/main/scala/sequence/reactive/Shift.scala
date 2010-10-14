

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Shift[+A](_1: Reactive[A], _2: util.ByName[Unit] => Unit) extends Forwarder[A] {
    override protected val delegate = _1.shiftReact{ x => f => _2(util.byName{f(x)}) }
}

private
case class ShiftReact[A](_1: Reactive[A], _2: A => (A => Unit) => Unit) extends Reactive[A] {
    override def close = _1.close
    override def foreach(f: A => Unit) {
        for (x <- _1) {
            _2(x)(f)
        }
    }
}
