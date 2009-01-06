

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object StringPeg {
    def apply(str: String): Peg[Char] = Peg.vectorPeg(Vector.stringVector(str))
}
