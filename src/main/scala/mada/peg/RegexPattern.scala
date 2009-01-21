

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


import java.util.regex.Pattern


object RegexPatternPeg {
    def apply(pat: Pattern): Peg[Char] = new RegexPatternPeg(pat)
}

class RegexPatternPeg(pat: Pattern) extends Peg[Char] {
    override def parse(v: Vector[Char], start: Int, end: Int): Int = {
        import Vector.Compatibles._
        val mat = pat.matcher(v)
        mat.region(start, end)
        mat.useTransparentBounds(true)
        if (mat.lookingAt) {
            mat.end
        } else {
            Peg.FAILURE
        }
    }
}
