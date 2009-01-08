

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


trait Compatibles {
    def madaPeg[A](from: Peg[A]): Peg[A] = from

    implicit def char2madaPeg(from: Char): Peg[Char] = Peg.single(from)
    implicit def string2madaPeg(from: String): Peg[Char] = Peg.stringPeg(from)
    implicit def madaVector2madaPeg[A](from: Vector[A]): Peg[A] = Peg.vectorPeg(from)

    /*
    class MadaPegVectorArrow[A](from: Vector[A]) {
        def -->(p: Peg[A]): (Vector[A], Peg[A]) = (from, p)
    }
    implicit def madaVector2MadaPegVectorArrow[A](from: Vector[A]): MadaPegVectorArrow[A] = new MadaPegVectorArrow(from)

    class MadaPegAnyArrow[A](from: A) {
        def -->(p: Peg[A]): (A, Peg[A]) = (from, p)
    }
    implicit def any2MadaPegAnyArrow[A](from: A): MadaPegAnyArrow[A] = new MadaPegAnyArrow(from)
    */
}
