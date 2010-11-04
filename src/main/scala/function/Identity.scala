

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package function


private
case class Identity[T]() extends Transform[T] {
    override def apply(x: T) = x
}


private
case class Identity1[T1]() extends Function1[T1, Tuple1[T1]] {
    override def apply(v1: T1) = Tuple1(v1)
}

private
case class Identity2[T1, T2]() extends Function2[T1, T2, (T1, T2)] {
    override def apply(v1: T1, v2: T2) = (v1, v2)
}

private
case class Identity3[T1, T2, T3]() extends Function3[T1, T2, T3, (T1, T2, T3)] {
    override def apply(v1: T1, v2: T2, v3: T3) = (v1, v2, v3)
}

private
case class Identity4[T1, T2, T3, T4]() extends Function4[T1, T2, T3, T4, (T1, T2, T3, T4)] {
    override def apply(v1: T1, v2: T2, v3: T3, v4: T4) = (v1, v2, v3, v4)
}

private
case class Identity5[T1, T2, T3, T4, T5]() extends Function5[T1, T2, T3, T4, T5, (T1, T2, T3, T4, T5)] {
    override def apply(v1: T1, v2: T2, v3: T3, v4: T4, v5: T5) = (v1, v2, v3, v4, v5)
}
