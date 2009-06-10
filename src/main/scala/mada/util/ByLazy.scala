

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.util


trait ByLazy[+R] extends Function0[R]


class ByLazyOf[+R](body: => R) extends ByLazy[R] {
    val _1: ByLazy[R] = this

    private lazy val v = body
    override def apply() = v
}
