

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package peg


private[dual]
trait Common {

    /**
     * Epsilon, the empty string
     */
     val eps: eps = new Eps
    type eps      =     Eps

    /**
     * Terminal
     */
     def term[y <: Any](y: y): term[y] = Term.apply(y)
    type term[y <: Any]                = Term.apply[y]

    /**
     * Dot, which matches any character.
     */
     val dot: dot = Dot.apply
    type dot      = Dot.apply

    /**
     * Matches only the given list.
     */
     def fromList[ys <: List](ys: ys): fromList[ys] = FromList.apply(ys)
    type fromList[ys <: List]                       = FromList.apply[ys]


    /**
     * Helps to build a recursive grammar.
     */
     def recursive[f <: Function0](f: f): recursive[f] = Recursive(f)
    type recursive[f <: Function0]                     = Recursive[f]

}
