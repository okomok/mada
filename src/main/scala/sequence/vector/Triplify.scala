

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


private
case class Triplify[A, B](_1: Func[A, B]) extends Func3[A, B] {
    override def apply(v: Vector[A], start: Int, end: Int) = _1(v(start, end))
}

private
case class Untriplify[A, B](_1: Func3[A, B]) extends Func[A, B] {
    override def apply(v: Vector[A]) = _1(v, v.start, v.end)
}
