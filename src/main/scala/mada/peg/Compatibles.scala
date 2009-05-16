

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * Contains implicit conversions around <code>Peg</code>.
 */
@compatibles
trait Compatibles { this: Peg.type =>
    implicit def madaPegFromChar(from: Char): Peg[Char] = fromChar(from)
    implicit def madaPegFromIterable[A](from: Iterable[A]): Peg[A] = fromIterable(from)
    implicit def madaPegFromRegex(from: scala.util.matching.Regex): Peg[Char] = fromRegexPattern(from.pattern)
    implicit def madaPegFromRegexPattern(from: java.util.regex.Pattern): Peg[Char] = fromRegexPattern(from)
    implicit def madaPegUnstringize(from: String): Peg[Char] = unstringize(from)
    implicit def madaPegFromVector[A](from: Vector[A]): Peg[A] = fromVector(from)
}
