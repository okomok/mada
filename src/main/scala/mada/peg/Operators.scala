

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.peg


/**
 * Contains operators which can't be method of vector because of type constraints.
 */
trait Operators { this: Peg.type =>
    sealed class MadaPegChar(_1: Peg[Char]) {
        def lowerCaseRead = Peg.lowerCaseRead(_1)
    }
    implicit def madaPegChar(_1: Peg[Char]): MadaPegChar = new MadaPegChar(_1)

    sealed class MadaPegByName[A](_1: => Peg[A]) {
        def `lazy` = Peg.`lazy`(_1)
    }
    implicit def madaPegByName[A](_1: => Peg[A]): MadaPegByName[A] = new MadaPegByName(_1)

    sealed class MadaPegIterablePeg[A](_1: Iterable[Peg[A]]) {
        def longest = Peg.longest(_1)
        def shortest = Peg.shortest(_1)
    }
    implicit def madaPegIterablePeg[A](_1: Iterable[Peg[A]]): MadaPegIterablePeg[A] = new MadaPegIterablePeg(_1)
}
