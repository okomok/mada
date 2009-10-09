

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package peg


case class Regex(_1: String) extends Forwarder[Char] {
    override protected val delegate = fromRegexPattern(java.util.regex.Pattern.compile(_1))
}

case class FromRegexPattern(_1: java.util.regex.Pattern) extends Peg[Char] {
    override def parse(v: sequence.Vector[Char], start: Int, end: Int) = {
        val mat = _1.matcher(v.toJCharSequence)
        mat.region(start - v.start, end - v.start)
        mat.useTransparentBounds(true)
        if (mat.lookingAt) {
            v.start + mat.end
        } else {
            FAILURE
        }
    }
}
