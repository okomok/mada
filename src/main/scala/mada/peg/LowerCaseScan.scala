

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object LowerCaseScan {
    def apply(p: Peg[Char]): Peg[Char] = p.unmap(java.lang.Character.toLowerCase(_))
}
