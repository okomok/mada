

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object FromString {
    def apply(str: String): Parser[Char] = Parser.fromVector(Vector.stringVector(str))
}
