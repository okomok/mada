

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative


case class ToList[A](_1: Iterative[A]) extends List[A] {
    private var it = _1.begin
    override def isNil = !it
    override def head = {
        preHead
        ~it
    }
    override def tail = {
        preTail
        it.++
        list.cons(~it, iterative.bind(it).toList)
    }
}
