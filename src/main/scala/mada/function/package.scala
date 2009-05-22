

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object function {


// void

    @aliasOf("()")
    val void: Unit = ()


// type aliases

    @aliasOf("Function1[T, T]")
    type Transform[T] = Function1[T, T]

    @aliasOf("Function1[T1, Boolean]")
    type Predicate1[-T1] = Function1[T1, Boolean]

    @aliasOf("Function2[T1, T2, Boolean]")
    type Predicate2[-T1, -T2] = Function2[T1, T2, Boolean]

    @aliasOf("Function3[T1, T2, T3, Boolean]")
    type Predicate3[-T1, -T2, -T3] = Function3[T1, T2, T3, Boolean]


// equal

    /**
     * @return  <code>{ (x, y) => x == y }</code>.
     */
    val equal: Predicate2[Any, Any] = Equal()

    /**
     * @return  <code>{ y => x == y }</code>.
     */
    def equalTo(x: Any): Predicate1[Any] = EqualTo(x)


// utilities

    /**
     * A function flipping two arguments
     */
    def flip[T1, T2, R](f: Function2[T1, T2, R]): Function2[T2, T1, R] = Flip(f)

    /**
     * A function returning argument as is
     */
    def identity[T]: Transform[T] = identityImpl.asInstanceOf[Transform[T]]
    private val identityImpl: Transform[Any] = { v => v }

    /**
     * Converts by-name-parameter to a function returning <code>body</code>.
     */
    def ofName[R](body: => R): Function0[R] = new OfName(body)

    /**
     * A function calculating <code>body</code> by <code>lazy</code>
     */
    def ofLazy[R](body: => R): Function0[R] = new OfLazy(body)

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
        val wrap_g = { (fixed: (T => R)) => (v: T) => assoc.lazyGet(m)(v){ g(fixed)(v) } }
        fix(wrap_g)
    }


// empty

    /**
     * Returns a function doing nothing.
     */
    val empty1: Function1[Any, Unit] = Empty1()
    val empty2: Function2[Any, Any, Unit] = Empty2()
    val empty3: Function3[Any, Any, Any, Unit] = Empty3()


// fuse

    /**
     * Converts a function into one taking a tuple argument.
     */
    def fuse1[T1, R](f: Function1[T1, R]): Function1[Tuple1[T1], R] = { v => f(v._1) }
    def fuse2[T1, T2, R](f: Function2[T1, T2, R]): Function1[Tuple2[T1, T2], R] = { v => f(v._1, v._2) }
    def fuse3[T1, T2, T3, R](f: Function3[T1, T2, T3, R]): Function1[Tuple3[T1, T2, T3], R] = { v => f(v._1, v._2, v._3) }

    def fuse[T1, R](f: Function1[T1, R]): Function1[Tuple1[T1], R] = fuse1(f)
    def fuse[T1, T2, R](f: Function2[T1, T2, R]): Function1[Tuple2[T1, T2], R] = fuse2(f)
    def fuse[T1, T2, T3, R](f: Function3[T1, T2, T3, R]): Function1[Tuple3[T1, T2, T3], R] = fuse3(f)

    /**
     * Reverts <code>fuse</code>.
     */
    def unfuse1[T1, R](f: Function1[Tuple1[T1], R]): Function1[T1, R] = { v1 => f(Tuple1(v1)) }
    def unfuse2[T1, T2, R](f: Function1[Tuple2[T1, T2], R]): Function2[T1, T2, R] = { (v1, v2) => f(Tuple2(v1, v2)) }
    def unfuse3[T1, T2, T3, R](f: Function1[Tuple3[T1, T2, T3], R]): Function3[T1, T2, T3, R] = { (v1, v2, v3) => f(Tuple3(v1, v2, v3)) }


// infer

    /**
     * Returns <code>f</code> as is, which helps type inference.
     */
    def infer1[T1, R](f: Function1[T1, R]): Function1[T1, R] = f
    def infer2[T1, T2, R](f: Function2[T1, T2, R]): Function2[T1, T2, R] = f
    def infer3[T1, T2, T3, R](f: Function3[T1, T2, T3, R]): Function3[T1, T2, T3, R] = f


    @packageObjectBrokenOverload
    object infer {
        def apply[T1, R](f: Function1[T1, R]): Function1[T1, R] = infer1(f)
        def apply[T1, T2, R](f: Function2[T1, T2, R]): Function2[T1, T2, R] = infer2(f)
        def apply[T1, T2, T3, R](f: Function3[T1, T2, T3, R]): Function3[T1, T2, T3, R] = infer3(f)
    }


// not

    /**
     * Negates a predicate.
     */
    def not1[T1](f: Predicate1[T1]): Predicate1[T1] = Not1(f)
    def not2[T1, T2](f: Predicate2[T1, T2]): Predicate2[T1, T2] = Not2(f)
    def not3[T1, T2, T3](f: Predicate3[T1, T2, T3]): Predicate3[T1, T2, T3] = Not3(f)

    @packageObjectBrokenOverload
    object not {
        def apply[T1](f: Predicate1[T1]): Predicate1[T1] = not1(f)
        def apply[T1, T2](f: Predicate2[T1, T2]): Predicate2[T1, T2] = not2(f)
        def apply[T1, T2, T3](f: Predicate3[T1, T2, T3]): Predicate3[T1, T2, T3] = not3(f)
    }


// parameterize

    private def getArg[T](ps: Iterable[Parameter[_]], q: Parameter[T]) = ps.find(_.origin eq q.origin).getOrElse(q).argument.asInstanceOf[T]

    def parameterize1[T1, R](f: Function1[T1, R])(q1: Parameter[T1]): Function1[Iterable[Parameter[_]], R] = new Function1[Iterable[Parameter[_]], R] {
        override def apply(ps: Iterable[Parameter[_]]) = f(getArg(ps, q1))
    }

    def parameterize2[T1, T2, R](f: Function2[T1, T2, R])(q1: Parameter[T1], q2: Parameter[T2]): Function1[Iterable[Parameter[_]], R] = new Function1[Iterable[Parameter[_]], R] {
        override def apply(ps: Iterable[Parameter[_]]) = f(getArg(ps, q1), getArg(ps, q2))
    }

    def parameterize3[T1, T2, T3, R](f: Function3[T1, T2, T3, R])(q1: Parameter[T1], q2: Parameter[T2], q3: Parameter[T3]): Function1[Iterable[Parameter[_]], R] = new Function1[Iterable[Parameter[_]], R] {
        override def apply(ps: Iterable[Parameter[_]]) = f(getArg(ps, q1), getArg(ps, q2), getArg(ps, q3))
    }

    @packageObjectBrokenOverload
    object parameterize {
        def apply[T1, R](f: Function1[T1, R])(q1: Parameter[T1]): Function1[Iterable[Parameter[_]], R] = parameterize1(f)(q1)
        def apply[T1, T2, R](f: Function2[T1, T2, R])(q1: Parameter[T1], q2: Parameter[T2]): Function1[Iterable[Parameter[_]], R] = parameterize2(f)(q1, q2)
        def apply[T1, T2, T3, R](f: Function3[T1, T2, T3, R])(q1: Parameter[T1], q2: Parameter[T2], q3: Parameter[T3]): Function1[Iterable[Parameter[_]], R] = parameterize3(f)(q1, q2, q3)
    }


// synchronize

    /**
     * Converts a function to synchronized one.
     */
    def synchronize1[T1, R](f: Function1[T1, R]): Function1[T1, R] = Synchronize1(f)
    def synchronize2[T1, T2, R](f: Function2[T1, T2, R]): Function2[T1, T2, R] = Synchronize2(f)
    def synchronize3[T1, T2, T3, R](f: Function3[T1, T2, T3, R]): Function3[T1, T2, T3, R] = Synchronize3(f)

    @packageObjectBrokenOverload
    object synchronize {
        def apply[T1, R](f: Function1[T1, R]): Function1[T1, R] = synchronize1(f)
        def apply[T1, T2, R](f: Function2[T1, T2, R]): Function2[T1, T2, R] = synchronize2(f)
        def apply[T1, T2, T3, R](f: Function3[T1, T2, T3, R]): Function3[T1, T2, T3, R] = synchronize3(f)
    }

}
