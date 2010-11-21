

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Drop[+A](_1: Reactive[A], _2: Int) extends Reactive[A] {
    Precondition.nonnegative(_2, "drop")

    override def close() = _1.close()
    override def forloop(f: A => Unit, k: Exit => Unit) {
        var c = _2
        _1 _for { x =>
            if (c == 0) {
                f(x)
            } else {
                c -= 1
            }
        } _then {
            k
        }
    }

    override def drop(n: Int): Reactive[A] = _1.drop(_2 + n) // drop.drop fusion
}
