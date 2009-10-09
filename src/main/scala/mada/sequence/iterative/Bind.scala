

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package sequence; package iterative


case class Bind[+A](_1: Iterator[A]) extends Iterative[A] {
    override def begin = _1
}

case class BindName[+A](_1: util.ByName[Iterator[A]]) extends Iterative[A] {
    override def begin = _1()
}
