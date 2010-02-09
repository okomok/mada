

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package blend; package list


@specializer
sealed abstract class Zip[l1 <: List, l2 <: List] extends ((l1, l2) => Zip.result[l1, l2])


object Zip {

    type result[l1 <: List, l2 <: List] = l1#accept_List[vt[l2]]

    sealed trait vt[l2 <: List] extends Visitor[List] {
        override type visitNil = Nil
        override type visitCons[h, t <: List] = Cons[Tuple2[h, l2#head], t#accept_List[vt[l2#tail]]]
    }

    implicit def ofNil[l2 <: List] = new Zip[Nil, l2] {
        override def apply(_l1: Nil, _l2: l2) = Nil
    }

    implicit def ofCons[h1, t1 <: List, h2, t2 <: List](implicit _zip: Zip[t1, t2]) = new Zip[Cons[h1, t1], Cons[h2, t2]] {
        override def apply(_l1: Cons[h1, t1], _l2: Cons[h2, t2]) = Cons(Tuple2(_l1.head, _l2.head), _zip(_l1.tail, _l2.tail))
    }

}
