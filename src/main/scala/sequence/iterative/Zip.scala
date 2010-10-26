

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterative


private
case class Zip[A, B](_1: Iterative[A], _2: Iterative[B]) extends Iterative[(A, B)] {
    override def begin = new Iterator[Tuple2[A, B]] {
        private[this] val it1 = _1.begin
        private[this] val it2 = _2.begin

        override def isEnd = !it1 || !it2
        override def deref = { preDeref; (~it1, ~it2) }
        override def increment = { preIncrement; it1.++; it2.++ }
    }
}
