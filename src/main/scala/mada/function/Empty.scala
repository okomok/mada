

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.function


case class Empty1() extends Function1[Any, Unit] {
    override def apply(v1: Any) = ()
}

case class Empty2() extends Function2[Any, Any, Unit] {
    override def apply(v1: Any, v2: Any) = ()
}

case class Empty3() extends Function3[Any, Any, Any, Unit] {
    override def apply(v1: Any, v2: Any, v3: Any) = ()
}
