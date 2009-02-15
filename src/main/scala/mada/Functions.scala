

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on <code>Function</code>.
 */
object Functions {
    import func._


// void

    /**
     * Alias of <code>()</code>
     */
    val void: Unit = ()


// type aliases

    /**
     * Alias of <code>T => T</code>
     */
    type Transform[T] = T => T

    /**
     * Alias of <code>Function1[T1, Boolean]</code>
     */
    type Predicate1[-T1] = Function1[T1, Boolean]

    /**
     * Alias of <code>Function2[T1, T2, Boolean]</code>
     */
    type Predicate2[-T1, -T2] = Function2[T1, T2, Boolean]

    /**
     * Alias of <code>Function3[T1, T2, T3, Boolean]</code>
     */
    type Predicate3[-T1, -T2, -T3] = Function3[T1, T2, T3, Boolean]

    /**
     * Alias of <code>Predicate2[T, T]</code>
     */
    type Compare[-T] = Predicate2[T, T]


// comparators

    /**
     * @return  <code>{ (x, y) => x == y }</code>.
     */
    val equal: Compare[Any] = { (x, y) => x == y }

    /**
     * @return  <code>{ y => x == y }</code>.
     */
    def equalTo(x: Any): Predicate1[Any] = { y => x == y }

    /**
     * @return  <code>{ (x, y) => c(x) < y }</code>.
     */
    def less[T](implicit c: T => Ordered[T]): Compare[T] = { (x, y) => c(x) < y }

    /**
     * @return  <code>{ (x, y) => c(x) > y }</code>.
     */
    def greater[T](implicit c: T => Ordered[T]): Compare[T] = { (x, y) => c(x) > y }

    /**
     * Converts from <code>java.util.Comparator</code> "less-than" predicate.
     */
    def fromComparator[A](from: java.util.Comparator[A]): Compare[A] = { (x, y) => from.compare(x, y) < 0 }

    /**
     * Converts "less-than" predicate to <code>java.util.Comparator</code>.
     */
    def toComparator[A](from: Compare[A]): java.util.Comparator[A] = new java.util.Comparator[A] {
        override def compare(x: A, y: A) = if (from(x, y)) -1 else if (from(y, x)) 1 else 0
    }


// utilities

    /**
     * A function flipping two arguments
     */
    def flip[T1, T2, R](f: Function2[T1, T2, R]): Function2[T2, T1, R] = { (v2, v1) => f(v1, v2) }

    /**
     * A function returning argument as is
     */
    def identity[T]: Transform[T] = { v => v }

    /**
     * Converts by-name-parameter to a function returing <code>v</code>.
     */
    def byName[R](v: => R): Function0[R] = { () => v }

    /**
     * Converts by-name-parameter to a function returing lazy <code>v</code>.
     */
    def byLazy[R](v: => R): Function0[R] = new Function0[R] {
        private lazy val _v = v
        override def apply() = _v
    }

    /**
     * Fixed point combinator
     */
    def fix[T, R](g: Transform[Function1[T, R]]): Function1[T, R] = { v => fixImpl(g)(v) }
    private def fixImpl[T, R](g: Transform[Function1[T, R]])(v: T): R = g(fixImpl(g))(v)

    /**
     * Memoizes <code>g</code> using hash map.
     */
    def memoize[T, R](g: Transform[Function1[T, R]]): Function1[T, R] = Memoize(g)


// not

    /**
     * Negates the predicate.
     */
    def not1[T1](f: Predicate1[T1]): Predicate1[T1] = { v1 => !f(v1) }

    /**
     * Negates the predicate.
     */
    def not2[T1, T2](f: Predicate2[T1, T2]): Predicate2[T1, T2] = { (v1, v2) => !f(v1, v2) }

    /**
     * Negates the predicate.
     */
    def not3[T1, T2, T3](f: Predicate3[T1, T2, T3]): Predicate3[T1, T2, T3] = { (v1, v2, v3) => !f(v1, v2, v3) }

    /**
     * Alias of <code>not1</code>
     */
    def not[T1](f: Predicate1[T1]): Predicate1[T1] = not1(f)

    /**
     * Alias of <code>not2</code>
     */
    def not[T1, T2](f: Predicate2[T1, T2]): Predicate2[T1, T2] = not2(f)

    /**
     * Alias of <code>not3</code>
     */
    def not[T1, T2, T3](f: Predicate3[T1, T2, T3]): Predicate3[T1, T2, T3] = not3(f)


// empty

    /**
     * A function to do nothing
     */
    val empty1: Function1[Any, Unit] = { v1 => () }

    /**
     * A function to do nothing
     */
    val empty2: Function2[Any, Any, Unit] = { (v1, v2) => () }

    /**
     * A function to do nothing
     */
    val empty3: Function3[Any, Any, Any, Unit] = { (v1, v2, v3) => () }


// Ref

    /**
     * Contains utility methods operating on <code>Function</code> and references.
     */
    object Ref {
        /**
         * @return  <code>{ (v1, v2) => v1 eq v2 }</code>.
         */
        val equal: Function2[AnyRef, AnyRef, Boolean] = { (v1, v2) => v1 eq v2 }

        /**
         * @return  <code>{ v2 => v1 eq v2 }</code>.
         */
        def equalTo(v1: AnyRef): Function1[AnyRef, Boolean] = { v2 => v1 eq v2 }
    }


// Typed

    /**
     * Contains utility methods operating on <code>Function</code> and typed references.
     */
    object Typed {
        /**
         * @return  <code>{ (v1, v2) => v1 == v2 }</code>.
         */
        def equal[T1, T2]: Function2[T1, T2, Boolean] = { (v1, v2) => v1 == v2 }
    }
}
