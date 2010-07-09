

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package peg


// So long names that wildcard import can unambiguously be used.

@compatibles
trait Compatibles { this: Peg.type =>
    implicit def _madaPegFromChar(from: Char): Peg[Char] = FromChar(from)
    implicit def _madaPegFromRegexPattern(from: java.util.regex.Pattern): Peg[Char] = FromRegexPattern(from)
    implicit def _madaPegUnstringize(from: String): Peg[Char] = Unstringize(from)
    implicit def _madaPegFromSequence[A](from: sequence.Iterative[A]): Peg[A] = FromSequence(from)
    implicit def _madaPegFromVector[A](from: sequence.Vector[A]): Peg[A] = FromSequence(from.asIterative)
    implicit def _madaPegFromSIterable[A](from: scala.Iterable[A]): Peg[A] = FromSIterable(from)
    implicit def _madaPegFromSRegex(from: scala.util.matching.Regex): Peg[Char] = FromSRegex(from)
}
