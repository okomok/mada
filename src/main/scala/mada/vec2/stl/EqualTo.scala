

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec2.stl


object EqualTo extends ((Any, Any) => Boolean) {
    override def apply(a: Any, b: Any): Boolean = a == b
}
