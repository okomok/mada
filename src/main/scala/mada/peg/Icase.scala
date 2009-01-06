

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Icase {
    def apply(str: String): Peg[Char] = {
        Peg.lowerCaseScan(Peg.vectorPeg(Vector.lowerCase(Vector.stringVector(str))))
    }
}
