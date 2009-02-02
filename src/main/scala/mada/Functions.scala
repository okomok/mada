

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on <code>Function</code>.
 */
object Functions {
    /**
     * Represents <code>{ (v1: Any, v2: Any) => v1 == v2 }</code>.
     */
    val equal: Function2[Any, Any, Boolean] = new Function2[Any, Any, Boolean] {
        override def apply(v1: Any, v2: Any) = v1 == v2
    }

    /**
     * Represents <code>(_: T1) == (_: T2)</code>.
     */
    def safeEqual[T1, T2]: Function2[T1, T2, Boolean] = new Function2[T1, T2, Boolean] {
        override def apply(v1: T1, v2: T2) = v1 == v2
    }

    /**
     * Represents <code>v1 == (_: Any)</code>.
     */
    def equalTo(v1: Any): Function1[Any, Boolean] = new Function1[Any, Boolean] {
        override def apply(v2: Any) = v1 == v2
    }

    /**
     * Representds <code>{ (v1, v2) => c(v1) < v2 }</code>.
     */
    def less[T1](implicit c: T1 => Ordered[T1]): Function2[T1, T1, Boolean] = new Function2[T1, T1, Boolean] {
        override def apply(v1: T1, v2: T1) = c(v1) < v2
    }

    /**
     * A function fliping two arguments
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
     * Negates the predicate.
     */
    def not1[T1, R](f: Function1[T1, Boolean]): Function1[T1, Boolean] = new Function1[T1, Boolean] {
        override def apply(v1: T1) = !f(v1)
    }

    /**
     * Negates the predicate.
     */
    def not2[T1, T2, R](f: Function2[T1, T2, Boolean]): Function2[T1, T2, Boolean] = new Function2[T1, T2, Boolean] {
        override def apply(v1: T1, v2: T2) = !f(v1, v2)
    }

    /**
     * Negates the predicate.
     */
    def not3[T1, T2, T3, R](f: Function3[T1, T2, T3, Boolean]): Function3[T1, T2, T3, Boolean] = new Function3[T1, T2, T3, Boolean] {
        override def apply(v1: T1, v2: T2, v3: T3) = !f(v1, v2, v3)
    }

    /**
     * @return <code>not1(f)</code>.
     */
    def not[T1, R](f: Function1[T1, Boolean]): Function1[T1, Boolean] = not1(f)

    /**
     * @return <code>not2(f)</code>.
     */
    def not[T1, T2, R](f: Function2[T1, T2, Boolean]): Function2[T1, T2, Boolean] = not2(f)

    /**
     * @return <code>not3(f)</code>.
     */
    def not[T1, T2, T3, R](f: Function3[T1, T2, T3, Boolean]): Function3[T1, T2, T3, Boolean] = not3(f)


    /**
     * Contains utility methods operating on <code>Function</code> and references.
     */
    object ref {
        /**
         * Represents <code>(_: AnyRef) eq (_: AnyRef)</code>.
         */
        val equal: Function2[AnyRef, AnyRef, Boolean] = new Function2[AnyRef, AnyRef, Boolean] {
            override def apply(v1: AnyRef, v2: AnyRef) = v1 eq v2
        }

        /**
         * Represents <code>v1 eq (_: AnyRef)</code>.
         */
        def equalTo(v1: AnyRef): Function1[AnyRef, Boolean] = new Function1[AnyRef, Boolean] {
            override def apply(v2: AnyRef) = v1 eq v2
        }
    }
}











