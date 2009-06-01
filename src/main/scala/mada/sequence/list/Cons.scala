

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.list


class Cons[+A](h: => A, t: => List[A]) extends List[A] {
    val _1 = function.ofLazy(h)
    val _2 = function.ofLazy(t)

    override def isEmpty = false
    override def head = _1()
    override def tail = _2()
}
