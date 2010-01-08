

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package blendtest; package listtest


import mada.blend._


class ConsTest {

    case class ComposeL[T1, R1, T2 <: List, R2 <: List](f: T1 => R1, g: T2 => R2) extends (T1 :: T2 => R1 :: R2) {
        override def apply(xs: T1 :: T2): R1 :: R2 = f(xs.head) :: g(xs.tail)
    }

}
