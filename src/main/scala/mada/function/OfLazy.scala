

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.function


class OfLazy[+R](body: => R) extends Function0[R] {
    val _1 = ofName(body)

    private lazy val v = body
    override def apply() = v
}
