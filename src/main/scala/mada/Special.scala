

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Used to associate type with value.
 */
class Special[Tag, T, R](f: T => R) extends (T => R) {
    override def apply(v: T) = f(v)
}
