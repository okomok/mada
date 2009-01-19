

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Regex {
    def apply(x: String): Peg[Char] = Peg.regexPatternPeg(java.util.regex.Pattern.compile(x))
}
