

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package dual; package list


private[mada] class FilterCons {
     def apply[x <: Any, xs <: List, f <: Function1](x: x, xs: xs, f: f): apply[x, xs, f] = throw new Error//f.apply(x).if_List(new Then(x, xs, f), new Else(x, xs, f)).apply
    type apply[x <: Any, xs <: List, f <: Function1] = Nothing//f#apply[x]#if_List[Then[x, xs, f], Else[x, xs, f]]#apply
/*
    class Then[x <: Any, xs <: List, f <: Function1_Any_Boolean](x: x, xs: xs, f: f) extends Function0_List {
        override  def apply: apply = Cons(x, xs.filter(f))
        override type apply = Cons[x, xs#filter[f]]
    }
    class Else[x <: Any, xs <: List, f <: Function1_Any_Boolean](x: x, xs: xs, f: f) extends Function0_List {
        override  def apply: apply = xs.filter(f)
        override type apply = xs#filter[f]
    }
*/
}
