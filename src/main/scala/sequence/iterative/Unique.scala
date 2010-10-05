

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package iterative


private
case class Unique[+A](_1: Iterative[A]) extends Forwarder[A] {
    override protected val delegate = _1.uniqueBy(function.equal)

    override def unique: Iterative[A] = this // unique-unique fusion
}

private
case class UniqueBy[A](_1: Iterative[A], _2: (A, A) => Boolean) extends Iterative[A] {
    override def begin = new Iterator[A] {
        private val it = _1.begin

        override def isEnd = !it
        override def deref = ~it
        override def increment = {
            val tmp = ~it
            it.++
            it.advanceWhile{ e => _2(tmp, e) }
        }
    }
}
