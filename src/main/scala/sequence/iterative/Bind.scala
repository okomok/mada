

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterative


private
case class Bind[+A](_1: Iterator[A]) extends Iterative[A] {
    override def begin = _1
}

private
case class BindName[+A](_1: util.ByName[Iterator[A]]) extends Iterative[A] {
    override def begin = _1()
}
