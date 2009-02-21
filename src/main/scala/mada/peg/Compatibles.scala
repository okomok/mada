

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * Contains implicit conversions around <code>Peg</code>.
 */
trait Compatibles {
    import Peg._

    implicit def madaPegFromChar(from: Char): Peg[Char] = fromChar(from)
    implicit def madaPegFromIterable[A](from: Iterable[A]): Peg[A] = fromIterable(from)
    implicit def madaPegFromRegex(from: scala.util.matching.Regex): Peg[Char] = fromRegexPattern(from.pattern)
    implicit def madaPegFromRegexPattern(from: java.util.regex.Pattern): Peg[Char] = fromRegexPattern(from)
    implicit def madaPegFromString(from: String): Peg[Char] = fromString(from)
    implicit def madaPegFromVector[A](from: Vector[A]): Peg[A] = fromVector(from)
}
