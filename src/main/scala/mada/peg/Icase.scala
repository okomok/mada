

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Icase {
    def apply(v: Vector[Char]): Peg[Char] = {
        Peg.lowerCaseRead(Peg.vectorPeg(Vector.lowerCase(v)))
    }
}
