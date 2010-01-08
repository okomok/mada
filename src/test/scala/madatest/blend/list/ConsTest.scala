

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package blendtest; package listtest


import mada.blend._


class ConsTest {

    /*
    case class Hmm_ComposeL[T1, R1, T2 <: List, R2 <: List](f: T1 => R1, g: T2 => R2) extends (T1 :: T2 => R1 :: R2) {
        override def apply(xs: T1 :: T2): R1 :: R2 = f(xs.head) :: g(xs.tail)
    }
    */

    trait Func1 {
        type Arg1
        type Result
        def apply(v1: Arg1): Result
    }

    case class ComposeL[f <: Func1, g <: Func1 { type Result <: List }](f: f, g: g) extends Func1 {
        override type Arg1 = f#Arg1 :: g#Arg1
        override type Result = f#Result :: g#Result
        override def apply(xs: Arg1) = f(xs.head) :: g(xs.tail)
    }

}
