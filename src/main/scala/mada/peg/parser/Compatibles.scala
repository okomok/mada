

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg.parser


object Compatibles {
    implicit def char2madaPegParser(from: Char): Parser[Char] = Parser.single(from)
    implicit def string2madaPegParser(from: String): Parser[Char] = Parser.fromString(from)
    implicit def madaVector2madaPegParser[A](from: Vector[A]): Parser[A] = Parser.fromVector(from)
}
