

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.util


class ByLazy[+R](body: => R) extends Function0[R] {
    val _1: Function0[R] = this

    private lazy val v = body
    override def apply() = v
}


object ByLazy {
    def unapply[R](that: Function0[R]): Option[Function0[R]] = that match {
        case that: ByLazy[_] => Some(that._1)
        case _ => None
    }
}
