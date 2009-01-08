

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Rule {
    def make1[A]: (Rule[A]) = (new Rule[A])
    def make2[A]: (Rule[A], Rule[A]) = (new Rule[A], new Rule[A])
    def make3[A]: (Rule[A], Rule[A], Rule[A]) = (new Rule[A], new Rule[A], new Rule[A])
    def make4[A]: (Rule[A], Rule[A], Rule[A], Rule[A]) = (new Rule[A], new Rule[A], new Rule[A], new Rule[A])
    def make5[A]: (Rule[A], Rule[A], Rule[A], Rule[A], Rule[A]) = (new Rule[A], new Rule[A], new Rule[A], new Rule[A], new Rule[A])
}

class Rule[A](private var p: Peg[A]) extends PegProxy[A] {
    def this() = this(null)

    override def self = p
    def ::=(that: Peg[A]): Unit = { p = that }
    def <--(that: Peg[A]): Unit = { p = that }
    def copy: Rule[A] = new Rule[A](p)
}
