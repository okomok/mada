

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


import func._


/**
 * Contains utility methods operating on <code>Function</code>.
 */
object Functions extends
    Empty with Fuse with Infer with Not with Parameterize with Synchronize { ^ =>


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

    /**
     * Alias of <code>func.Parameter[T]</code>
     */
    type Parameter[T] = func.Parameter[T]


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
    def identity[T]: Transform[T] = identityImpl.asInstanceOf[Transform[T]]
    private val identityImpl: Transform[Any] = { v => v }

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


// Ref

    /**
     * Contains utility methods operating on <code>Function</code> and references.
     */
    object Ref {
        /**
         * @return  <code>{ (v1, v2) => v1 eq v2 }</code>.
         */
        val equal: Predicate2[AnyRef, AnyRef] = { (v1, v2) => v1 eq v2 }

        /**
         * @return  <code>{ v2 => v1 eq v2 }</code>.
         */
        def equalTo(v1: AnyRef): Predicate1[AnyRef] = { v2 => v1 eq v2 }
    }


// Typed

    /**
     * Contains utility methods operating on <code>Function</code> and typed references.
     */
    object Typed {
        /**
         * @return  <code>{ (v1, v2) => v1 == v2 }</code>.
         */
        def equal[T1, T2]: Predicate2[T1, T2] = ^.equal.asInstanceOf[Predicate2[T1, T2]]
    }
}
