

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package util


case class ByName[+R](_1: Function0[R]) extends Function0[R] {
    override def apply = _1()
}
