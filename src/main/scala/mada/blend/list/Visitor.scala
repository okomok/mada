

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package blend; package list


/**
 * Just like iterator of the meta List.
 */
trait Visitor[R] {
    type visitNil <: R
    type visitCons[h, t <: List] <: R
}
