

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.blend


package object list {


    /**
     * Creates a list from <code>sequence.List</code>.
     */
    def typed[l <: List](from: sequence.List[Any])(implicit _typed: Typed[l]) = _typed(from)


// conversion

    def fromTuple1[T1](from: Tuple1[T1]): T1 :: Nil = from._1 :: Nil
    def fromTuple2[T1, T2](from: Tuple2[T1, T2]): T1 :: T2 :: Nil = from._1 :: from._2 :: Nil
    def fromTuple3[T1, T2, T3](from: Tuple3[T1, T2, T3]): T1 :: T2 :: T3 :: Nil = from._1 :: from._2 :: from._3 :: Nil
    def fromTuple4[T1, T2, T3, T4](from: Tuple4[T1, T2, T3, T4]): T1 :: T2 :: T3 :: T4 :: Nil = from._1 :: from._2 :: from._3 :: from._4 :: Nil
    def fromTuple5[T1, T2, T3, T4, T5](from: Tuple5[T1, T2, T3, T4, T5]): T1 :: T2 :: T3 :: T4 :: T5 :: Nil = from._1 :: from._2 :: from._3 :: from._4 :: from._5 :: Nil

    @packageObjectBrokenOverload
    object fromTuple {
        def apply[T1](from: Tuple1[T1]): T1 :: Nil = fromTuple1(from)
        def apply[T1, T2](from: Tuple2[T1, T2]): T1 :: T2 :: Nil = fromTuple2(from)
        def apply[T1, T2, T3](from: Tuple3[T1, T2, T3]): T1 :: T2 :: T3 :: Nil = fromTuple3(from)
        def apply[T1, T2, T3, T4](from: Tuple4[T1, T2, T3, T4]): T1 :: T2 :: T3 :: T4 :: Nil = fromTuple4(from)
        def apply[T1, T2, T3, T4, T5](from: Tuple5[T1, T2, T3, T4, T5]): T1 :: T2 :: T3 :: T4 :: T5 :: Nil = fromTuple5(from)
    }

}
