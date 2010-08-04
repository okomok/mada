

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package modeltest


import com.github.okomok.mada
import mada.dual._
import mada.dual.nat.dense.Literal._
import mada.dual.nat.dense.{Nil, ::, _1B, _0B}


object FastFibonacci {
     def fibonacci[n <: Nat](n: n): fibonacci[n] = `if`(n  < _2,  Const0(n), FibElse(n)).apply.asInstanceOfNat
    type fibonacci[n <: Nat]                     = `if`[n# <[_2], Const0[n], FibElse[n]]#apply#asInstanceOfNat

    case class FibElse[n <: Nat](n: n) extends Function0 {
        type self = FibElse[n]
        override  def apply: apply = (fibonacci(n.decrement)  + fibonacci(n.decrement.decrement)).asInstanceOf[apply]
        override type apply        =  fibonacci[n#decrement]# +[fibonacci[n#decrement#decrement]]
    }
}

object SlowFibonacci {
     def fibonacci[n <: Nat](n: n): fibonacci[n] = `if`(n  < _2,  Const0(n), FibElse(n)).apply.asInstanceOfNat
    type fibonacci[n <: Nat]                     = `if`[n# <[_2], Const0[n], FibElse[n]]#apply#asInstanceOfNat

    case class FibElse[n <: Nat](n: n) extends Function0 {
        type self = FibElse[n]
        // Now child node type expressions are different.
        override  def apply: apply = (fibonacci(n  - _1)   + fibonacci(n.decrement.decrement)).asInstanceOf[apply]
        override type apply        =  fibonacci[n# -[_1]]# +[fibonacci[n#decrement#decrement]]
    }
}
