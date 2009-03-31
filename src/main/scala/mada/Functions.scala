

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import func._


/**
 * Contains utility methods operating on <code>Function</code>.
 */
object Functions {


// void

    /**
     * Alias of <code>()</code>
     */
    val void: Unit = ()


// type aliases

    /**
     * Alias of <code>Function1[T, T]</code>
     */
    type Transform[T] = Function1[T, T]

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


// equal

    /**
     * @return  <code>{ (x, y) => x == y }</code>.
     */
    val equal: Predicate2[Any, Any] = { (x, y) => x == y }

    /**
     * @return  <code>{ y => x == y }</code>.
     */
    def equalTo(x: Any): Predicate1[Any] = { y => x == y }


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
     * Converts by-name-parameter to a function returning <code>body</code>.
     */
    def byName[R](body: => R): Function0[R] = { () => body }

    /**
     * A function calculating <code>body</code> by <code>lazy</code>
     */
    def byLazy[R](body: => R): Function0[R] = new Function0[R] {
        private lazy val _v = body
        override def apply() = _v
    }

    /**
     * A function calculating <code>body</code> in (possibly) other threads
     */
    def future[R](body: => R): Function0[R] = Future(body)

    /**
     * Fixed point combinator
     */
    def fix[T, R](g: (T => R) => T => R): T => R = { v => fixImpl(g)(v) }
    private def fixImpl[T, R](g: (T => R) => T => R)(v: T): R = g(fixImpl(g))(v)

    /**
     * Memoizes <code>g</code>.
     */
    def memoize[T, R](g: (T => R) => T => R): T => R = {
        // See: That about wraps it up --- Using FIX to handle errors without exceptions, and other programming tricks (1997)
        //      at http://citeseer.ist.psu.edu/51062.html
        val m = new java.util.concurrent.ConcurrentHashMap[T, () => R]
        val wrap_g = { (fixed: (T => R)) => (v: T) => Maps.lazyGet(m)(v){ g(fixed)(v) } }
        fix(wrap_g)
    }


// infer

    /**
     * @return  <code>f</code>.
     */
    def infer1[T1, R](f: Function1[T1, R]): Function1[T1, R] = f

    /**
     * @return  <code>f</code>.
     */
    def infer2[T1, T2, R](f: Function2[T1, T2, R]): Function2[T1, T2, R] = f

    /**
     * @return  <code>f</code>.
     */
    def infer3[T1, T2, T3, R](f: Function3[T1, T2, T3, R]): Function3[T1, T2, T3, R] = f

    /**
     * Alias of <code>infer1</code>
     */
    def infer[T1, R](f: Function1[T1, R]): Function1[T1, R] = infer1(f)

    /**
     * Alias of <code>infer2</code>
     */
    def infer[T1, T2, R](f: Function2[T1, T2, R]): Function2[T1, T2, R] = infer2(f)

    /**
     * Alias of <code>infer3</code>
     */
    def infer[T1, T2, T3, R](f: Function3[T1, T2, T3, R]): Function3[T1, T2, T3, R] = infer3(f)


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


// fuse

    /**
     * @return  <code>{ v => f(1._1)) }</code>.
     */
    def fuse1[T1, R](f: Function1[T1, R]): Function1[Tuple1[T1], R] = { v => f(v._1) }

    /**
     * @return  <code>{ v => f(v._1, v._2) }</code>.
     */
    def fuse2[T1, T2, R](f: Function2[T1, T2, R]): Function1[Tuple2[T1, T2], R] = { v => f(v._1, v._2) }

    /**
     * @return  <code>{ v => f(v._1, v._2, v._3) }</code>.
     */
    def fuse3[T1, T2, T3, R](f: Function3[T1, T2, T3, R]): Function1[Tuple3[T1, T2, T3], R] = { v => f(v._1, v._2, v._3) }

    /**
     * Alias of <code>fuse1</code>
     */
    def fuse[T1, R](f: Function1[T1, R]): Function1[Tuple1[T1], R] = fuse1(f)

    /**
     * Alias of <code>fuse2</code>
     */
    def fuse[T1, T2, R](f: Function2[T1, T2, R]): Function1[Tuple2[T1, T2], R] = fuse2(f)

    /**
     * Alias of <code>fuse3</code>
     */
    def fuse[T1, T2, T3, R](f: Function3[T1, T2, T3, R]): Function1[Tuple3[T1, T2, T3], R] = fuse3(f)


// unfuse

    /**
     * @return  <code>{ v1 => f(Tuple1(v1)) }</code>.
     */
    def unfuse1[T1, R](f: Function1[Tuple1[T1], R]): Function1[T1, R] = { v1 => f(Tuple1(v1)) }

    /**
     * @return  <code>{ (v1, v2) => f(Tuple2(v1, v2)) }</code>.
     */
    def unfuse2[T1, T2, R](f: Function1[Tuple2[T1, T2], R]): Function2[T1, T2, R] = { (v1, v2) => f(Tuple2(v1, v2)) }

    /**
     * @return  <code>{ (v1, v2, v3) => f(Tuple3(v1, v2, v3)) }</code>.
     */
    def unfuse3[T1, T2, T3, R](f: Function1[Tuple3[T1, T2, T3], R]): Function3[T1, T2, T3, R] = { (v1, v2, v3) => f(Tuple3(v1, v2, v3)) }


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


// synchronized, which memoize etc may need.

    /**
     * Returns synchronized one.
     */
    def synchronized1[T1, R](f: Function1[T1, R]): Function1[T1, R] = new Function1[T1, R] {
        override def apply(v1: T1) = synchronized { f(v1) }
    }

    /**
     * Returns synchronized one.
     */
    def synchronized2[T1, T2, R](f: Function2[T1, T2, R]): Function2[T1, T2, R] = new Function2[T1, T2, R] {
        override def apply(v1: T1, v2: T2) = synchronized { f(v1, v2) }
    }

    /**
     * Returns synchronized one.
     */
    def synchronized3[T1, T2, T3, R](f: Function3[T1, T2, T3, R]): Function3[T1, T2, T3, R] = new Function3[T1, T2, T3, R] {
        override def apply(v1: T1, v2: T2, v3: T3) = synchronized { f(v1, v2, v3) }
    }

    /**
     * Alias of <code>synchronized1</code>
     */
    def `synchronized`[T1, R](f: Function1[T1, R]): Function1[T1, R] = synchronized1(f)

    /**
     * Alias of <code>synchronized2</code>
     */
    def `synchronized`[T1, T2, R](f: Function2[T1, T2, R]): Function2[T1, T2, R] = synchronized2(f)

    /**
     * Alias of <code>synchronized3</code>
     */
    def `synchronized`[T1, T2, T3, R](f: Function3[T1, T2, T3, R]): Function3[T1, T2, T3, R] = synchronized3(f)


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
