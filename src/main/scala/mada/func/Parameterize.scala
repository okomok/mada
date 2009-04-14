

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.func


/**
 * Represents named parameter.
 */
trait Parameter[A] {
    /**
     * Returns <code>this</code> as parameter's unique id.
     */
    def origin: Parameter[A] = this

    /**
     * The argument passed to this parameter
     */
    def argument: A = throw new IllegalArgumentException(toString + " argument missing")

    /**
     * Alias of <code>pass</code>
     */
    final def apply(v: A): Parameter[A] = pass(v)

    /**
     * Alias of <code>pass</code>
     */
    final def ->(v: A): Parameter[A] = pass(v)

    /**
     * Passes the argument to this parameter.
     */
    final def pass(v: A): Parameter[A] = new Parameter[A] {
        override def origin = Parameter.this
        override def argument = v
    }
}


private[mada] object Parameterize {
    def apply1[T1, R](f: Function1[T1, R], q1: Parameter[T1]): Function1[Seq[Parameter[_]], R] = new Function1[Seq[Parameter[_]], R] {
        override def apply(ps: Seq[Parameter[_]]) = f(getArg[T1](ps, 0, q1))
    }

    def apply2[T1, T2, R](f: Function2[T1, T2, R], q1: Parameter[T1], q2: Parameter[T2]): Function1[Seq[Parameter[_]], R] = new Function1[Seq[Parameter[_]], R] {
        override def apply(ps: Seq[Parameter[_]]) = f(getArg[T1](ps, 0, q1), getArg[T2](ps, 1, q2))
    }

    def apply3[T1, T2, T3, R](f: Function3[T1, T2, T3, R], q1: Parameter[T1], q2: Parameter[T2], q3: Parameter[T3]): Function1[Seq[Parameter[_]], R] = new Function1[Seq[Parameter[_]], R] {
        override def apply(ps: Seq[Parameter[_]]) = f(getArg[T1](ps, 0, q1), getArg[T2](ps, 1, q2), getArg[T3](ps, 2, q3))
    }

    private def getArg[T](ps: Seq[Parameter[_]], n: Int, q: Parameter[_]): T = {
        val _p = ps.find(_.origin eq q.origin)
        if (_p.isEmpty) {
            q.argument.asInstanceOf[T]
        } else {
            _p.get.argument.asInstanceOf[T]
        }
    }
}
