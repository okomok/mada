

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object LowerCaseScan {
    def apply(p: Parser[Char]): Parser[Char] = p.unmap(java.lang.Character.toLowerCase(_))
}
