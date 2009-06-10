

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.util


trait ByName[+R] extends Function0[R]


class ByNameOf[+R](body: => R) extends ByName[R] {
    val _1: ByName[R] = this

    override def apply() = body
}
