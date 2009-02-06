

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


import java.util.regex.Pattern


private[mada] object FromRegexPattern {
    def apply(pat: Pattern): Peg[Char] = new RegexPatternPeg(pat)
}

private[mada] class RegexPatternPeg(pat: Pattern) extends Peg[Char] {
    override def parse(v: Vector[Char], start: Int, end: Int): Int = {
        val mat = pat.matcher(v)
        mat.region(start - v.start, end - v.start)
        mat.useTransparentBounds(true)
        if (mat.lookingAt) {
            v.start + mat.end
        } else {
            Peg.FAILURE
        }
    }
}
