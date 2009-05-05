

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.func


/**
 * Converts a function into parameterized one.
 */
@provider
trait Parameterize { this: Functions.type =>
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

    def parameterize[T1, R](f: Function1[T1, R])(q1: Parameter[T1]): Function1[Iterable[Parameter[_]], R] = parameterize1(f)(q1)
    def parameterize[T1, T2, R](f: Function2[T1, T2, R])(q1: Parameter[T1], q2: Parameter[T2]): Function1[Iterable[Parameter[_]], R] = parameterize2(f)(q1, q2)
    def parameterize[T1, T2, T3, R](f: Function3[T1, T2, T3, R])(q1: Parameter[T1], q2: Parameter[T2], q3: Parameter[T3]): Function1[Iterable[Parameter[_]], R] = parameterize3(f)(q1, q2, q3)
}


/**
 * Represents named parameter.
 */
trait Parameter[A] { ^ =>
    /**
     * Returns <code>this</code> as parameter's unique id.
     */
    def origin: Parameter[A] = this

    /**
     * The argument passed to this parameter
     */
    def argument: A = throw new IllegalArgumentException("missing argument: " + this)

    /**
     * Alias of <code>pass</code>
     */
    final def ->(v: A): Parameter[A] = pass(v)

    /**
     * Passes the argument to this parameter.
     */
    final def pass(v: A): Parameter[A] = new Parameter[A] {
        override def origin = ^
        override def argument = v
    }
}
