

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * Provides implicit conversions around pegs.
 * These implicit conversion methods shall not called explicitly.
 */
object Compatibles {
    /**
     * Triggers implicit conversion "explicitly". Also works as the ambiguity buster.
     */
    def madaPeg[A](from: Peg[A]): Peg[A] = from

    implicit def char2madaPeg(from: Char): Peg[Char] = Peg.single(from)
    implicit def regexPattern2madaPeg(from: java.util.regex.Pattern): Peg[Char] = Peg.regexPatternPeg(from)
    implicit def string2madaPeg(from: String): Peg[Char] = Peg.stringPeg(from)
    implicit def madaVector2madaPeg[A](from: Vector[A]): Peg[A] = Peg.vectorPeg(from)
}
