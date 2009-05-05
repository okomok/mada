

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


/**
 * Contains operators missing from <code>Iterable</code>.
 */
@provider
trait Operators { this: Iterables.type =>
    @returnthis val Operators: Operators = this

    sealed class MadaIterables[A](_1: Iterable[A]) {
        def equal[B](_2: Iterable[B]) = Iterables.equal(_1, _2)
        def equalIf[B](_2: Iterable[B])(_3: (A, B) => Boolean) = Iterables.equalIf(_1, _2)(_3)
        def filter_(_2: A => Boolean) = Iterables.filter(_1)(_2)
        def folderLeft[B](_2: B)(_3: (B, A) => B) = Iterables.folderLeft(_1, _2)(_3)
        def reducerLeft[B >: A](_2: (B, A) => B) = Iterables.reducerLeft[A, B](_1)(_2)
        def length = Iterables.length(_1)
        def seal = Iterables.seal(_1)
        def step(_2: Int) = Iterables.step(_1, _2)
        def takeWhile_(_2: A => Boolean) = Iterables.takeWhile(_1)(_2)
        def cycle = Iterables.cycle(_1)
        def cycle(_2: Int) = Iterables.cycle(_1, _2)
        def toHashSet = Iterables.toHashSet(_1)
        def toString_ = Iterables.toString(_1)
        def unique = Iterables.unique(_1)
        def uniqueBy(_2: (A, A) => Boolean) = Iterables.uniqueBy(_1)(_2)
        def withSideEffect(_2: A => Unit) = Iterables.withSideEffect(_1)(_2)

        def merge(_2: Iterable[A])(implicit _3: Compare[A]) = Iterables.merge(_1)(_2)(_3)
        def mergeBy(_2: Iterable[A])(_3: Compare.Func[A]) = Iterables.mergeBy(_1)(_2)(_3)
        def union(_2: Iterable[A])(implicit _3: Compare[A]) = Iterables.union(_1)(_2)(_3)
        def unionBy(_2: Iterable[A])(_3: Compare.Func[A]) = Iterables.unionBy(_1)(_2)(_3)
        def intersection(_2: Iterable[A])(implicit _3: Compare[A]) = Iterables.intersection(_1)(_2)(_3)
        def intersectionBy(_2: Iterable[A])(_3: Compare.Func[A]) = Iterables.intersectionBy(_1)(_2)(_3)
        def difference(_2: Iterable[A])(implicit _3: Compare[A]) = Iterables.difference(_1)(_2)(_3)
        def differenceBy(_2: Iterable[A])(_3: Compare.Func[A]) = Iterables.differenceBy(_1)(_2)(_3)
        def symmetricDifference(_2: Iterable[A])(implicit _3: Compare[A]) = Iterables.symmetricDifference(_1)(_2)(_3)
        def symmetricDifferenceBy(_2: Iterable[A])(_3: Compare.Func[A]) = Iterables.symmetricDifferenceBy(_1)(_2)(_3)
    }
    implicit def madaIterables[A](_1: Iterable[A]): MadaIterables[A] = new MadaIterables(_1)

    sealed class MadaIterablesIterable[A](_1: Iterable[Iterable[A]]) {
        def flatten = Iterables.flatten(_1)
    }
    implicit def madaIterablesIterable[A](_1: Iterable[Iterable[A]]): MadaIterablesIterable[A] = new MadaIterablesIterable(_1)

    sealed class MadaIterablesByName[A](_1: => Iterable[A]) {
        def `lazy` = Iterables.`lazy`(_1)
    }
    implicit def madaIterablesByName[A](_1: => Iterable[A]): MadaIterablesByName[A] = new MadaIterablesByName(_1)
}
