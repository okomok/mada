

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.util


class ByName[+R](body: => R) extends Function0[R] {
    val _1: Function0[R] = this

    override def apply() = body
}
