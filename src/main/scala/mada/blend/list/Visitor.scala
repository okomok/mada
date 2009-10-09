

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package blend; package list


/**
 * Just like iterator of the meta List.
 */
trait Visitor {
    type Result
    type visitNil <: Result
    type visitCons[h, t <: List] <: Result
}
