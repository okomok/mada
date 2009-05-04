

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


import Meta._


/**
 * The meta iterable
 */
object Iterable {

    sealed trait by[it <: Iterator] extends Iterable {
        override type elements = it
    }

}

trait Iterable {
    type elements <: Iterator

/*


    type state = Product2 { type _T1 <: Iterator; type _T2 <: Int }

    trait pred extends Function1 {
        override type Argument11 = state
        override type apply1[s <: state] = s#_1#hasNext
    }

    trait update extends Function1 {
        override type Argument11 = state
        override type apply1[s <: state] = Tuple2[Iterator, Int, s#_1#toNext, s#_2#increment]
    }

    type length = `while`[Tuple2[Iterator, Int, elements, _0I], pred, state]#_2

    */

/*
    type foldLeft[z, op <: elements#FoldLeftFunction[z]] = elements#foldLeft[z, op]#apply0
*/
}


trait Iterator {

    type hasNext <: Boolean
    type head
    type toNext <: Iterator

/*
    type FoldLeftFunction[B] = Meta.Function2 { type Argument21 >: B; type Argument22 >: Element; type apply2[b <: Argument21, a <: Argument22] <: B }
    sealed trait foldLeft[z, op <: FoldLeftFunction[z]] extends Meta.Function0 {
        override type apply0 = `if`[hasNext, toNext#foldLeft[op#apply2[z, head], op], always[z]]#apply0
    }
*/
}



























