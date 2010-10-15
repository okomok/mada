

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


private
case class Replace[A](_1: Reactive[A], _2: Iterative[A]) extends Reactive[A] {
    override def close = _1.close
    override def foreach(f: A => Unit) {
        val it = _2.begin
        for (x <- _1) {
            if (it) {
                f(~it)
                it.++
            } else {
                f(x)
            }
        }
    }
}

private
case class ReplaceRegion[A](_1: Reactive[A], _2: Int, _3: Int, _4: Iterative[A]) extends Reactive[A] {
    override def close = _1.close
    override def foreach(f: A => Unit) = _1.fork{r => r.slice(_2, _3).replace(_4).foreach(f)}.drop(_3).foreach(f)
}
