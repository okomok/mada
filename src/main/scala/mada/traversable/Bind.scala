

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class Bind[A](_1: Traverser[A]) extends Traversable[A] {
    override def begin = _1
}

class BindName[A](t: => Traverser[A]) extends Traversable[A] {
    val _1 = function.ofName(t)
    override def begin = _1()
}
