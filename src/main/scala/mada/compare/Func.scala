

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package compare


case class FromFunc[A](_1: Func[A]) extends Compare[A] {
    override def apply(x: A, y: A) = _1(x, y)
}
