

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


// step 0 is meaningful?


private
case class Step[+A](_1: Reactive[A], _2: Int) extends Reactive[A] {
    override def close = _1.close
    Precondition.positive(_2, "step")

    override def foreach(f: A => Unit) = {
        var c = 0
        for (x <- _1) {
            if (c == 0) {
                f(x)
            }
            c += 1
            if (c == _2) {
                c = 0
            }
        }
    }

    override def step(n: Int): Reactive[A] = _1.step(_2 * n) // step-step fusion
}
