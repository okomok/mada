

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.list


case class Cons[+A](_1: function.OfLazy[A], _2: function.OfLazy[List[A]]) extends List[A] {
    override def isNil = false
    override def head = _1()
    override def tail = _2()
}
