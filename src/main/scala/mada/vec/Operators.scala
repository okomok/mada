

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


/**
 * Contains operators which can't be method of vector because of type constraints.
 */
trait Operators { this: Vector.type =>
    @returnthis val Operators: Operators = this

    sealed class MadaVectorIterableVector[A](_1: Iterable[Vector[A]]) {
        def flatten = Vector.flatten(_1)
        def unsplit(_2: Vector[A]) = Vector.unsplit(_1)(_2)
    }
    implicit def madaVectorIterableVector[A](_1: Iterable[Vector[A]]): MadaVectorIterableVector[A] = new MadaVectorIterableVector(_1)

    sealed class MadaVectorEither[A, B](_1: Vector[Either[A, B]]) {
        def lefts = Vector.lefts(_1)
        def rights = Vector.rights(_1)
    }
    implicit def madaVectorEither[A, B](_1: Vector[Either[A, B]]): MadaVectorEither[A, B] = new MadaVectorEither(_1)

    sealed class MadaVectorVector[A](_1: Vector[Vector[A]]) {
        def undivide = Vector.undivide(_1)
    }
    implicit def madaVectorVector[A](_1: Vector[Vector[A]]): MadaVectorVector[A] = new MadaVectorVector(_1)

    sealed class MadaVectorPair[A, B](_1: Vector[(A, B)]) {
        def unzip = Vector.unzip(_1)
    }
    implicit def madaVectorPair[A, B](_1: Vector[(A, B)]): MadaVectorPair[A, B] = new MadaVectorPair(_1)

    sealed class MadaVectorByName[A](_1: => Vector[A]) {
        def `lazy` = Vector.`lazy`(_1)
    }
    implicit def madaVectorByName[A](_1: => Vector[A]): MadaVectorByName[A] = new MadaVectorByName(_1)

    sealed class MadaVectorChar(_1: Vector[Char]) {
        def lowerCase = Vector.lowerCase(_1)
        def upperCase = Vector.upperCase(_1)
        def stringize = Vector.stringize(_1)
    }
    implicit def madaVectorChar(_1: Vector[Char]): MadaVectorChar = new MadaVectorChar(_1)
}
