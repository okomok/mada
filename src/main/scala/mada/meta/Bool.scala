

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


// See: http://www.assembla.com/wiki/show/metascala


import Meta.Func


/**
 * Compile-time boolean
 */
sealed trait Bool {
    private[mada] type And[that <: Bool] <: Bool
    private[mada] type Or[that <: Bool] <: Bool
    private[mada] type Not <: Bool
    private[mada] type If[then, else_]
    private[mada] type LazyIf[then <: Func, else_ <: Func] <: Func
}


/**
 * Compile-time true
 */
final class True extends Bool {
    private[mada] type And[that <: Bool] = that
    private[mada] type Or[that <: Bool] = True
    private[mada] type Not = False
    private[mada] type If[then, else_] = then
    private[mada] type LazyIf[then <: Func, else_ <: Func] = then
}

/**
 * Compile-time false
 */
final class False extends Bool {
    private[mada] type And[that <: Bool] = False
    private[mada] type Or[that <: Bool] = that
    private[mada] type Not = True
    private[mada] type If[then, else_] = else_
    private[mada] type LazyIf[then <: Func, else_ <: Func] = else_
}
