

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


/**
 * Provides infix operators using implicit conversions.
 */
object Infix {
    /**
     * Intermediate class for infix operators.
     */
    sealed class MadaIterables[A](_1: Iterable[A]) {
        /**
         * @return  <code>Iterables.equal(_2)</code>.
         */
        def equal[B](_2: Iterable[B]) = Iterables.equal(_1, _2)

        /**
         * @return  <code>Iterables.equalBy(_1, _2)(_3)</code>.
         */
        def equalBy[B](_2: Iterable[B])(_3: Functions.Predicate2[A, B]) = Iterables.equalBy(_1, _2)(_3)

        /**
         * @return  <code>Iterables.filter(_1)(_2)</code>.
         */
        def filter_(_2: A => Boolean) = Iterables.filter(_1)(_2)

        /**
         * @return  <code>Iterables.folderLeft(_1, _2)(_3)</code>.
         */
        def folderLeft[B](_2: B)(_3: (B, A) => B) = Iterables.folderLeft(_1, _2)(_3)

        /**
         * @return  <code>Iterables.reducerLeft(_1)(_2)</code>.
         */
        def reducerLeft[B >: A](_2: (B, A) => B) = Iterables.reducerLeft[A, B](_1)(_2)

        /**
         * @return  <code>Iterables.length(_1)</code>.
         */
        def length = Iterables.length(_1)

        /**
         * @return  <code>Iterables.seal(_1)</code>.
         */
        def seal = Iterables.seal(_1)

        /**
         * @return  <code>Iterables.step(_1, _2)</code>.
         */
        def step(_2: Int) = Iterables.step(_1, _2)

        /**
         * @return  <code>Iterables.takeWhile(_1)(_2)</code>.
         */
        def takeWhile_(_2: A => Boolean) = Iterables.takeWhile(_1)(_2)

        /**
         * @return  <code>Iterables.toHashSet(_1)</code>.
         */
        def toHashSet = Iterables.toHashSet(_1)

        /**
         * @return  <code>Iterables.withSideEffect(_1)(_2)</code>.
         */
        def withSideEffect(_2: A => Unit) = Iterables.withSideEffect(_1)(_2)
    }

    /**
     * @return  <code>new MadaIterables(_1)</code>.
     */
    implicit def madaIteratorToMadaIterables[A](_1: Iterable[A]): MadaIterables[A] = new MadaIterables(_1)
}
