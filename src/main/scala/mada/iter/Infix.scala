

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
    sealed class MadaIterators[A](_1: Iterator[A]) {
        /**
         * @return  <code>Iterators.equal(_2)</code>.
         */
        def equal[B](_2: Iterator[B]) = Iterators.equal(_1, _2)

        /**
         * @return  <code>Iterators.equalWith(_1, _2)(_3)</code>.
         */
        def equalWith[B](_2: Iterator[B])(_3: Functions.Predicate2[A, B]) = Iterators.equalWith(_1, _2)(_3)

        /**
         * @return  <code>Iterators.filter(_1)(_2)</code>.
         */
        def filter_(_2: A => Boolean) = Iterators.filter(_1)(_2)

        /**
         * @return  <code>Iterators.folderLeft(_1, _2)(_3)</code>.
         */
        def folderLeft[B](_2: B)(_3: (B, A) => B) = Iterators.folderLeft(_1, _2)(_3)

        /**
         * @return  <code>Iterators.reducerLeft(_1)(_2)</code>.
         */
        def reducerLeft[B >: A](_2: (B, A) => B) = Iterators.reducerLeft[A, B](_1)(_2)

        /**
         * @return  <code>Iterators.isEmpty(_1)</code>.
         */
        def isEmpty = Iterators.isEmpty(_1)

        /**
         * @return  <code>Iterators.length(_1)</code>.
         */
        def length = Iterators.length(_1)

        /**
         * @return  <code>Iterators.seal(_1)</code>.
         */
        def seal = Iterators.seal(_1)

        /**
         * @return  <code>Iterators.step(_1, _2)</code>.
         */
        def step(_2: Int) = Iterators.step(_1, _2)

        /**
         * @return  <code>Iterators.takeWhile(_1)(_2)</code>.
         */
        def takeWhile_(_2: A => Boolean) = Iterators.takeWhile(_1)(_2)

        /**
         * @return  <code>Iterators.toHashSet(_1)</code>.
         */
        def toHashSet = Iterators.toHashSet(_1)

        /**
         * @return  <code>Iterators.withSideEffect(_1)(_2)</code>.
         */
        def withSideEffect(_2: A => Any) = Iterators.withSideEffect(_1)(_2)
    }

    /**
     * @return  <code>new MadaIterators(_1)</code>.
     */
    implicit def madaIteratorToMadaIterators[A](_1: Iterator[A]): MadaIterators[A] = new MadaIterators(_1)
}
