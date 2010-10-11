

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package iterative


private
case class Adjacent[+A](_1: Iterative[A]) extends Iterative[(A, A)] {
    override def begin = {
        val it = _1.begin
        if (!it) {
            iterator.end
        } else {
            new Iterator[(A, A)] {
                private var prev = ~it
                it.++
                override def isEnd = !it
                override def deref = (prev, ~it)
                override def increment = {
                    prev = ~it
                    it.++
                }
            }
        }
    }
}
