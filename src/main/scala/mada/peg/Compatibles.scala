

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


object Compatibles {
    def madaPeg[A](from: Peg[A]): Peg[A] = from

    implicit def char2madaPeg(from: Char): Peg[Char] = Peg.single(from)
    implicit def regex2madaPeg(from: java.util.regex.Pattern): Peg[Char] = Peg.regexPeg(from)
    implicit def string2madaPeg(from: String): Peg[Char] = Peg.stringPeg(from)
    implicit def madaVector2madaPeg[A](from: Vector[A]): Peg[A] = Peg.vectorPeg(from)

    def madaPegAction[A, B](from: (Vector[A], Long, Long) => B): (Vector[A], Long, Long) => B = from
    implicit def madaPegUnaryAction2TripleAction[A, B](from: Vector[A] => B): (Vector[A], Long, Long) => B = Vector.triplify(from)
}
