

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


trait Common {

    /**
     * Makes a List Equiv from element Equiv.
     */
     def eqv[ee <: Equiv](ee: ee): eqv[ee] = new Eqv(ee)
    type eqv[ee <: Equiv] = Eqv[ee]

    @equivalentTo("new Nil{}")
     val Nil = _Nil.value

    @equivalentTo("x# ::[xs]")
    type ::[x <: Any, xs <: List] = xs# ::[x]

    @equivalentTo("xs# ++[ys]")
    type ++[xs <: List, ys <: List] = xs# ++[ys]

    @equivalentTo("Nil.::(x)")
     def single[x <: Any](x: x): single[x] = Nil. ::(x)
    type single[x <: Any]                  = Nil# ::[x]

    /**
     * Forces tuple elements.
     */
     def force2[t <: Product2](t: t): force2[t] = Tuple2(t._1.asInstanceOfList.force, t._2.asInstanceOfList.force)
    type force2[t <: Product2]                  = Tuple2[t#_1#asInstanceOfList#force, t#_2#asInstanceOfList#force]

    def fromSTuple1[T1](from: scala.Tuple1[T1]): Box[T1] :: Nil = Box(from._1) :: Nil
    def fromSTuple2[T1, T2](from: scala.Tuple2[T1, T2]): Box[T1] :: Box[T2] :: Nil = Box(from._1) :: Box(from._2) :: Nil
    def fromSTuple3[T1, T2, T3](from: scala.Tuple3[T1, T2, T3]): Box[T1] :: Box[T2] :: Box[T3] :: Nil = Box(from._1) :: Box(from._2) :: Box(from._3) :: Nil
    def fromSTuple4[T1, T2, T3, T4](from: scala.Tuple4[T1, T2, T3, T4]): Box[T1] :: Box[T2] :: Box[T3] :: Box[T4] :: Nil = Box(from._1) :: Box(from._2) :: Box(from._3) :: Box(from._4) :: Nil
    def fromSTuple5[T1, T2, T3, T4, T5](from: scala.Tuple5[T1, T2, T3, T4, T5]): Box[T1] :: Box[T2] :: Box[T3] :: Box[T4] :: Box[T5] :: Nil = Box(from._1) :: Box(from._2) :: Box(from._3) :: Box(from._4) :: Box(from._5) :: Nil

    def fromSTuple[T1](from: scala.Tuple1[T1]): Box[T1] :: Nil = fromSTuple1(from)
    def fromSTuple[T1, T2](from: scala.Tuple2[T1, T2]): Box[T1] :: Box[T2] :: Nil = fromSTuple2(from)
    def fromSTuple[T1, T2, T3](from: scala.Tuple3[T1, T2, T3]): Box[T1] :: Box[T2] :: Box[T3] :: Nil = fromSTuple3(from)
    def fromSTuple[T1, T2, T3, T4](from: scala.Tuple4[T1, T2, T3, T4]): Box[T1] :: Box[T2] :: Box[T3] :: Box[T4] :: Nil = fromSTuple4(from)
    def fromSTuple[T1, T2, T3, T4, T5](from: scala.Tuple5[T1, T2, T3, T4, T5]): Box[T1] :: Box[T2] :: Box[T3] :: Box[T4] :: Box[T5] :: Nil = fromSTuple5(from)

}
