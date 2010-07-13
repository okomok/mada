

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


trait Common {

    @equivalentTo("new Nil{}")
    val Nil: Nil = _List.Nil

    @aliasOf("Cons")
    val :: = Cons

    @equivalentTo("x#addFirst[xs]")
    type ::[x <: Any, xs <: List] = xs#addFirst[x]

    @equivalentTo("ys# :::[xs]")
    type :::[xs <: List, ys <: List] = ys# :::[xs]

    @equivalentTo("xs#reverse_:::[ys]")
    type reverse_:::[xs <: List, ys <: List] = ys#reverse_:::[xs]

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

    /**
     * Creates a list from <code>sequence.List</code>.
     */
  //  def dualed[l <: List](from: sequence.List[scala.Any])(implicit _dualed: Dualed[l]) = _dualed(from)

}
