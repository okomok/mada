

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on <code>Function</code>.
 */
object Functions {


// unit

    /**
     * Alias of <code>()</code>
     */
    val void: Unit = ()


// type aliases

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
     * Alias of <code>Predicate2[T1, T1]</code>
     */
    type Compare[-T1] = Predicate2[T1, T1]


// comparators

    /**
     * Represents <code>{ (v1: Any, v2: Any) => v1 == v2 }</code>.
     */
    val equal: Compare[Any] = new Compare[Any] {
        override def apply(v1: Any, v2: Any) = v1 == v2
    }

    /**
     * Represents <code>{ (v2: Any) => v1 == v2 }</code>.
     */
    def equalTo(v1: Any): Predicate1[Any] = new Predicate1[Any] {
        override def apply(v2: Any) = v1 == v2
    }

    /**
     * Represents <code>{ (v1: T1, v2: T1) => c(v1) < v2 }</code>.
     */
    def less[T1](implicit c: T1 => Ordered[T1]): Compare[T1] = new Compare[T1] {
        override def apply(v1: T1, v2: T1) = c(v1) < v2
    }

    /**
     * Represents <code>{ (v1: T1, v2: T1) => c(v1) > v2 }</code>.
     */
    def greater[T1](implicit c: T1 => Ordered[T1]): Compare[T1] = new Compare[T1] {
        override def apply(v1: T1, v2: T1) = c(v1) > v2
    }

    /**
     * Converts from <code>java.util.Comparator</code> "less-than" predicate.
     */
    def fromComparator[A](from: java.util.Comparator[A]): Compare[A] = new Compare[A] {
        override def apply(x: A, y: A) = from.compare(x, y) < 0
    }

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
    def flip[T1, T2, R](f: Function2[T1, T2, R]): Function2[T2, T1, R] = new Function2[T2, T1, R] {
        override def apply(v2: T2, v1: T1) = f(v1, v2)
    }

    /**
     * A function returning argument as is
     */
    def identity[T1]: Function1[T1, T1] = new Function1[T1, T1] {
        override def apply(v1: T1) = v1
    }

    /**
     * Converts by-name-parameter to a function returing <code>v</code>.
     */
    def byName[R](v: => R): Function0[R] = new Function0[R] {
        override def apply() = v
    }

    /**
     * Converts by-name-parameter to a function returing lazy <code>v</code>.
     */
    def byLazy[R](v: => R): Function0[R] = new Function0[R] {
        private lazy val _v = v
        override def apply() = _v
    }


// not

    /**
     * Negates the predicate.
     */
    def not1[T1, R](f: Predicate1[T1]): Predicate1[T1] = new Predicate1[T1] {
        override def apply(v1: T1) = !f(v1)
    }

    /**
     * Negates the predicate.
     */
    def not2[T1, T2, R](f: Predicate2[T1, T2]): Predicate2[T1, T2] = new Predicate2[T1, T2] {
        override def apply(v1: T1, v2: T2) = !f(v1, v2)
    }

    /**
     * Negates the predicate.
     */
    def not3[T1, T2, T3, R](f: Predicate3[T1, T2, T3]): Predicate3[T1, T2, T3] = new Predicate3[T1, T2, T3] {
        override def apply(v1: T1, v2: T2, v3: T3) = !f(v1, v2, v3)
    }

    /**
     * @return <code>not1(f)</code>.
     */
    def not[T1, R](f: Predicate1[T1]): Predicate1[T1] = not1(f)

    /**
     * @return <code>not2(f)</code>.
     */
    def not[T1, T2, R](f: Predicate2[T1, T2]): Predicate2[T1, T2] = not2(f)

    /**
     * @return <code>not3(f)</code>.
     */
    def not[T1, T2, T3, R](f: Predicate3[T1, T2, T3]): Predicate3[T1, T2, T3] = not3(f)


// empty

    /**
     * A function to do nothing
     */
    val empty1: Function1[Any, Unit] = new Function1[Any, Unit] {
        override def apply(v1: Any) = ()
    }

    /**
     * A function to do nothing
     */
    val empty2: Function2[Any, Any, Unit] = new Function2[Any, Any, Unit] {
        override def apply(v1: Any, v2: Any) = ()
    }

    /**
     * A function to do nothing
     */
    val empty3: Function3[Any, Any, Any, Unit] = new Function3[Any, Any, Any, Unit] {
        override def apply(v1: Any, v2: Any, v3: Any) = ()
    }


// Ref

    /**
     * Contains utility methods operating on <code>Function</code> and references.
     */
    object Ref {
        /**
         * Represents <code>{ (v1: AnyRef, v2: AnyRef) => v1 eq v2 }</code>.
         */
        val equal: Function2[AnyRef, AnyRef, Boolean] = new Function2[AnyRef, AnyRef, Boolean] {
            override def apply(v1: AnyRef, v2: AnyRef) = v1 eq v2
        }

        /**
         * Represents <code>{ (v2: AnyRef) => v1 eq v2 }</code>.
         */
        def equalTo(v1: AnyRef): Function1[AnyRef, Boolean] = new Function1[AnyRef, Boolean] {
            override def apply(v2: AnyRef) = v1 eq v2
        }
    }


// Typed

    /**
     * Contains utility methods operating on <code>Function</code> and typed references.
     */
    object Typed {
        /**
         * Represents <code>{ (v1: T1, v2: T2) => v1 == v2 }</code>.
         */
        def equal[T1, T2]: Function2[T1, T2, Boolean] = new Function2[T1, T2, Boolean] {
            override def apply(v1: T1, v2: T2) = v1 == v2
        }
    }
}
