

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package peg


@compatibles
trait Compatibles { this: Peg.type =>
    implicit def _madaPegFromChar(from: Char): Peg[Char] = fromChar(from)
    implicit def _madaPegFromRegexPattern(from: java.util.regex.Pattern): Peg[Char] = fromRegexPattern(from)
    implicit def _madaPegUnstringize(from: String): Peg[Char] = unstringize(from)
    implicit def _madaPegFromSequence[A](from: sequence.Iterative[A]): Peg[A] = fromSequence(from)
    implicit def _madaPegFromVector[A](from: sequence.Vector[A]): Peg[A] = fromSequence(from.asIterative)
    implicit def _madaPegFromSIterable[A](from: scala.Iterable[A]): Peg[A] = fromSIterable(from)
    implicit def _madaPegFromSRegex(from: scala.util.matching.Regex): Peg[Char] = fromSRegex(from)
}
