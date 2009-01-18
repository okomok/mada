

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Rule {
    def new1[A]: (Rule[A]) = (new Rule[A])
    def new2[A]: (Rule[A], Rule[A]) = (new Rule[A], new Rule[A])
    def new3[A]: (Rule[A], Rule[A], Rule[A]) = (new Rule[A], new Rule[A], new Rule[A])
    def new4[A]: (Rule[A], Rule[A], Rule[A], Rule[A]) = (new Rule[A], new Rule[A], new Rule[A], new Rule[A])
    def new5[A]: (Rule[A], Rule[A], Rule[A], Rule[A], Rule[A]) = (new Rule[A], new Rule[A], new Rule[A], new Rule[A], new Rule[A])
}

class Rule[A] private (private var p: Peg[A]) extends PegProxy[A] {
    def this() = this(null)

    override def self = p
    def ::=(that: Peg[A]): Unit = { p = that }
    override def clone: Rule[A] = new Rule[A](p)
}
