

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * Contains explicit conversions around <code>Peg</code>. (must be used through the <code>Peg</code> object.)
 */
trait Conversions {
    def fromChar(from: Char): Peg[Char] = Single(from)
    def fromIterable[A](from: Iterable[A]): Peg[A] = FromIterable(from)
    def fromRegex(from: scala.util.matching.Regex): Peg[Char] = FromRegexPattern(from.pattern)
    def fromRegexPattern(from: java.util.regex.Pattern): Peg[Char] = FromRegexPattern(from)
    def fromString(from: String): Peg[Char] = FromVector(Vector.fromString(from))
    def fromVector[A](from: Vector[A]): Peg[A] = FromVector(from)

    /**
     * Tries to match <code>from</code> using the predicate.
     */
    def fromIterableWith[A](from: Iterable[A])(pred: (A, A) => Boolean): Peg[A] = FromIterable(from, pred)

    /**
     * Tries to match <code>from</code> using the predicate.
     */
    def fromStringWith(from: String)(pred: (Char, Char) => Boolean): Peg[Char] = FromVector(Vector.fromString(from), pred)

    /**
     * Tries to match <code>from</code> using the predicate.
     */
    def fromVectorWith[A](from: Vector[A])(pred: (A, A) => Boolean): Peg[A] = FromVector(from, pred)
}