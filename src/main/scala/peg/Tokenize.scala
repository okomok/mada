

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package peg


private
case class Tokenize[A](_1: Peg[A], _2: sequence.Vector[A]) extends sequence.Iterative[sequence.Vector[A]] {
    override def begin = new sequence.Iterator[sequence.Vector[A]] {
        private[this] var (k, l) = _1.findRange(_2, _2.start, _2.end)

        override def isEnd = l == FAILURE
        override def deref = {
            preDeref
            new sequence.vector.Region(_2, k, l)
        }
        override def increment = {
            preIncrement
            k_l_assign(_1.findRange(_2, l, _2.end))
        }

        private def k_l_assign(r: (Int, Int)): Unit = {
            k = r._1; l = r._2
        }
    }
}
