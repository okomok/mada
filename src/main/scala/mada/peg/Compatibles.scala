

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


@compatibles
trait Compatibles { this: Peg.type =>
    implicit def madaPegFromChar(from: Char): Peg[Char] = fromChar(from)
    implicit def madaPegFromRegexPattern(from: java.util.regex.Pattern): Peg[Char] = fromRegexPattern(from)
    implicit def madaPegUnstringize(from: String): Peg[Char] = unstringize(from)
    implicit def madaPegFromSequence[A](from: sequence.Iterative[A]): Peg[A] = fromSequence(from)
    implicit def madaPegFromSIterable[A](from: scala.Iterable[A]): Peg[A] = fromSIterable(from)
    implicit def madaPegFromSRegex(from: scala.util.matching.Regex): Peg[Char] = fromSRegex(from)
}
