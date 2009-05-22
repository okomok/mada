

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.function


case class Equal() extends Predicate2[Any, Any] {
    override def apply(x: Any, y: Any) = x == y
}

case class EqualTo(_1: Any) extends Predicate1[Any] {
    override def apply(y: Any) = _1 == y
}
