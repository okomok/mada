

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


import java.util.regex.Pattern


object RegexPeg {
    def apply(pat: Pattern): Peg[Char] = new RegexPeg(pat)
}

class RegexPeg(pat: Pattern) extends Peg[Char] {
    override def parse(v: Vector[Char], first: Long, last: Long): Long = {
        import Vector.Compatibles._
        val mat = pat.matcher(v)
        mat.region(first.toInt, last.toInt)
        mat.useTransparentBounds(true)
        if (mat.lookingAt) {
            mat.end
        } else {
            Peg.FAILURE
        }
    }
}
