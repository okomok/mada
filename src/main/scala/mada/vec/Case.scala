

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


private[mada] object LowerCase {
    def apply(v: Vector[Char]): Vector[Char] = v.map(java.lang.Character.toLowerCase(_))
}

private[mada] object UpperCase {
    def apply(v: Vector[Char]): Vector[Char] = v.map(java.lang.Character.toUpperCase(_))
}
