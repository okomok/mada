

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada; package bound


// See: http://cleverlytitled.blogspot.com/2009/03/disjoint-bounded-views-redux.html


sealed abstract class ||[T1, T2]

object || {

    // T1 || T2 || T3
    // --> ||[||[T1, T2], T3]
    // --> ||[S1, S2] (S1 = ||[T1, T2], S2 = T3)

    implicit def of21[T1, T2](from: T1) = new (T1 || T2){}
    implicit def of22[T1, T2](from: T2) = new (T1 || T2){}

    implicit def of31[T1, T2, T3](from: T1) = new (T1 || T2 || T3){}
    implicit def of32[T1, T2, T3](from: T2) = new (T1 || T2 || T3){}

    implicit def of41[T1, T2, T3, T4](from: T1) = new (T1 || T2 || T3 || T4){}
    implicit def of42[T1, T2, T3, T4](from: T2) = new (T1 || T2 || T3 || T4){}

    implicit def of51[T1, T2, T3, T4, T5](from: T1) = new (T1 || T2 || T3 || T4 || T5){}
    implicit def of52[T1, T2, T3, T4, T5](from: T2) = new (T1 || T2 || T3 || T4 || T5){}

    implicit def of61[T1, T2, T3, T4, T5, T6](from: T1) = new (T1 || T2 || T3 || T4 || T5 || T6){}
    implicit def of62[T1, T2, T3, T4, T5, T6](from: T2) = new (T1 || T2 || T3 || T4 || T5 || T6){}

    implicit def of71[T1, T2, T3, T4, T5, T6, T7](from: T1) = new (T1 || T2 || T3 || T4 || T5 || T6 || T7){}
    implicit def of72[T1, T2, T3, T4, T5, T6, T7](from: T2) = new (T1 || T2 || T3 || T4 || T5 || T6 || T7){}

}
