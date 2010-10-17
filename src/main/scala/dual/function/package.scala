

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


package object function {

    /**
     * The unary constant function
     */
     def const0[v <: Any](v: => v): const0[v] = new Const0(v)
    type const0[v <: Any]                     =     Const0[v]

    /**
     * The identity function
     */
     val identity: identity = new Identity
    type identity           =     Identity

    /**
     * An unary function to throw an exception
     */
     def throw0(x: Throwable) = new Throw0(x)
    type throw0[_]            =     Throw0

    /**
     * Lifts `scala.FunctionN` to dual one.
     */
    def lift[R](x: () => R): Lift0[R] = lift0(x)
    def lift[T1, R](x: T1 => R): Lift1[T1, R] = lift1(x)
    def lift[T1, T2, R](x: (T1, T2) => R): Lift2[T1, T2, R] = lift2(x)
    def lift[T1, T2, T3, R](x: (T1, T2, T3) => R): Lift3[T1, T2, T3, R] = lift3(x)
    def lift0[R](x: () => R): Lift0[R] = Lift0(x)
    def lift1[T1, R](x: T1 => R): Lift1[T1, R] = Lift1(x)
    def lift2[T1, T2, R](x: (T1, T2) => R): Lift2[T1, T2, R] = Lift2(x)
    def lift3[T1, T2, T3, R](x: (T1, T2, T3) => R): Lift3[T1, T2, T3, R] = Lift3(x)

}
