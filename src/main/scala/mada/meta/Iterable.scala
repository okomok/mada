

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


import Meta._


/**
 * Contains meta assertions.
 */
object Iterable {

}

trait Iterable {
    type Element <: Object

    type elements <: Iterator { type Element <: Iterable#Element }

/*


    type state = Product2 { type _T1 <: Iterator; type _T2 <: Nat }

    sealed trait pred extends Function1 {
        override type Argument11 = state
        override type Result1 = Boolean
        override type apply1[s <: state] = s#_1#hasNext
    }

    sealed trait update extends Function1 {
        override type Argument11 = state
        override type Result1 = state
        override type apply1[s <: state] = Tuple2[Iterator, Nat, s#_1#toNext, s#_2#increment]
    }

    type length = `while`[Tuple2[Iterator, Nat, elements, _0N], pred, state]#_2

    */
}


trait Iterator extends Object {
    type Element <: Object

    type hasNext[_] <: Boolean
    type head[_] <: Element
    type toNext[_] <: Iterator
}
