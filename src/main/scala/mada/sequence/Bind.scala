

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


case class Bind[+A](_1: Iterator[A]) extends Sequence[A] {
    override def begin = _1
}

class BindName[+A](it: => Iterator[A]) extends Sequence[A] {
    val _1 = function.ofName(it)
    override def begin = _1()
}
