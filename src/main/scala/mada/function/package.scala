

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


package object function {


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

    @equivalentTo("{ (x, y) => x == y }")
    val equal: Predicate2[Any, Any] = Equal()

    @equivalentTo("{ y => x == y }")
    def equalTo(x: Any): Predicate1[Any] = EqualTo(x)


// utilities

    /**
     * A function flipping two arguments
     */
    def flip[T1, T2, R](f: Function2[T1, T2, R]): Function2[T2, T1, R] = Flip(f)

    /**
     * A function returning argument as is
     */
    def identity[T]: Transform[T] = Identity[T]()

    /**
     * Fixed point combinator
     */
    def fix[T, R](g: (T => R) => T => R): T => R = Fix(g)

    /**
     * Memoizes <code>g</code>.
     */
    def memoize[T, R](g: (T => R) => T => R): T => R = Memoize(g)

    /**
     * Skips calls n-times.
     */
    def skip[T](n: Int, f: Function1[T, Unit]): Function1[T, Unit] = Skip(n, f)


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
    def fuse1[T1, R](f: Function1[T1, R]): Function1[Tuple1[T1], R] = Fuse1(f)
    def fuse2[T1, T2, R](f: Function2[T1, T2, R]): Function1[Tuple2[T1, T2], R] = Fuse2(f)
    def fuse3[T1, T2, T3, R](f: Function3[T1, T2, T3, R]): Function1[Tuple3[T1, T2, T3], R] = Fuse3(f)

    def fuse[T1, R](f: Function1[T1, R]) = fuse1(f)
    def fuse[T1, T2, R](f: Function2[T1, T2, R]) = fuse2(f)
    def fuse[T1, T2, T3, R](f: Function3[T1, T2, T3, R]) = fuse3(f)

    /**
     * Reverts <code>fuse</code>.
     */
    def unfuse1[T1, R](f: Function1[Tuple1[T1], R]): Function1[T1, R] = Unfuse1(f)
    def unfuse2[T1, T2, R](f: Function1[Tuple2[T1, T2], R]): Function2[T1, T2, R] = Unfuse2(f)
    def unfuse3[T1, T2, T3, R](f: Function1[Tuple3[T1, T2, T3], R]): Function3[T1, T2, T3, R] = Unfuse3(f)


// infer

    /**
     * Returns <code>f</code> as is, which helps type inference.
     */
    def infer1[T1, R](f: Function1[T1, R]): Function1[T1, R] = f
    def infer2[T1, T2, R](f: Function2[T1, T2, R]): Function2[T1, T2, R] = f
    def infer3[T1, T2, T3, R](f: Function3[T1, T2, T3, R]): Function3[T1, T2, T3, R] = f

    def infer[T1, R](f: Function1[T1, R]) = infer1(f)
    def infer[T1, T2, R](f: Function2[T1, T2, R]) = infer2(f)
    def infer[T1, T2, T3, R](f: Function3[T1, T2, T3, R]) = infer3(f)


// not

    /**
     * Negates a predicate.
     */
    def not1[T1](f: Predicate1[T1]): Predicate1[T1] = Not1(f)
    def not2[T1, T2](f: Predicate2[T1, T2]): Predicate2[T1, T2] = Not2(f)
    def not3[T1, T2, T3](f: Predicate3[T1, T2, T3]): Predicate3[T1, T2, T3] = Not3(f)

    def not[T1](f: Predicate1[T1]) = not1(f)
    def not[T1, T2](f: Predicate2[T1, T2]) = not2(f)
    def not[T1, T2, T3](f: Predicate3[T1, T2, T3]) = not3(f)


// synchronize

    /**
     * Converts a function to synchronized one.
     */
    def synchronize1[T1, R](f: Function1[T1, R]): Function1[T1, R] = Synchronize1(f)
    def synchronize2[T1, T2, R](f: Function2[T1, T2, R]): Function2[T1, T2, R] = Synchronize2(f)
    def synchronize3[T1, T2, T3, R](f: Function3[T1, T2, T3, R]): Function3[T1, T2, T3, R] = Synchronize3(f)

    def synchronize[T1, R](f: Function1[T1, R]) = synchronize1(f)
    def synchronize[T1, T2, R](f: Function2[T1, T2, R]) = synchronize2(f)
    def synchronize[T1, T2, T3, R](f: Function3[T1, T2, T3, R]) = synchronize3(f)


// parameterize (deprecated with 2.8)

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

    def parameterize[T1, R](f: Function1[T1, R])(q1: Parameter[T1]) = parameterize1(f)(q1)
    def parameterize[T1, T2, R](f: Function2[T1, T2, R])(q1: Parameter[T1], q2: Parameter[T2]) = parameterize2(f)(q1, q2)
    def parameterize[T1, T2, T3, R](f: Function3[T1, T2, T3, R])(q1: Parameter[T1], q2: Parameter[T2], q3: Parameter[T3]) = parameterize3(f)(q1, q2, q3)

}
