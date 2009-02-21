

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * Contains implicit conversions around <code>Peg</code>.
 */
trait Compatibles {
    private val madaPeg = new Conversions { }

    implicit def madaPegFromChar(from: Char): Peg[Char] = madaPeg.fromChar(from)
    implicit def madaPegFromIterable[A](from: Iterable[A]): Peg[A] = madaPeg.fromIterable(from)
    implicit def madaPegFromRegex(from: scala.util.matching.Regex): Peg[Char] = madaPeg.fromRegexPattern(from.pattern)
    implicit def madaPegFromRegexPattern(from: java.util.regex.Pattern): Peg[Char] = madaPeg.fromRegexPattern(from)
    implicit def madaPegFromString(from: String): Peg[Char] = madaPeg.fromString(from)
    implicit def madaPegFromVector[A](from: Vector[A]): Peg[A] = madaPeg.fromVector(from)
}
