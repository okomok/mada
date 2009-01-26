

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


private[mada] object LowerCaseRead {
    def apply(p: Peg[Char]): Peg[Char] = p.readMap(Vector.lowerCase(_))
}
