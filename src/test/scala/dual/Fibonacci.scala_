

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest


import com.github.okomok.mada
import mada.dual._
import mada.dual.nat.dense.Literal._



object SlowFibonacci {
     def fibonacci[n <: Nat](n: n): fibonacci[n] = `if`(n  < _2,  Const0(n), FibElse(n)).apply.asInstanceOfNat
    type fibonacci[n <: Nat]                     = `if`[n# <[_2], Const0[n], FibElse[n]]#apply#asInstanceOfNat

    case class FibElse[n <: Nat](n: n) extends Function0 {
        type self = FibElse[n]
        override  def apply: apply = (fibonacci(n  - _1)   + fibonacci(n.decrement.decrement)).asInstanceOf[apply]
        override type apply        =  fibonacci[n# -[_1]]# +[fibonacci[n#decrement#decrement]]
    }
}

object SlowFibonacci2 {
     def fibonacci[n <: Nat, q <: nat.Peano](n: n, q: q): fibonacci[n, q] = `if`(n  < _2,  Const0(n), FibElse(n, q)).apply.asInstanceOfNat
    type fibonacci[n <: Nat, q <: nat.Peano]                              = `if`[n# <[_2], Const0[n], FibElse[n, q]]#apply#asInstanceOfNat

    case class FibElse[n <: Nat, q <: nat.Peano](n: n, q: q) extends Function0 {
        type self = FibElse[n, q]
        override  def apply: apply = (fibonacci(n.decrement, q.decrement)  + fibonacci(n.decrement.decrement, q.decrement)).asInstanceOf[apply]
        override type apply        =  fibonacci[n#decrement, q.decrement]# +[fibonacci[n#decrement#decrement, q#decrement]]
    }
}

object SlowFibonacci3 {
     def fibonacci[n <: Nat, q <: nat.Peano](n: n, q: q): fibonacci[n, q] = `if`(n  < _2,  Const0(n), FibElse(n, q)).apply.asInstanceOfNat
    type fibonacci[n <: Nat, q <: nat.Peano]                              = `if`[n# <[_2], Const0[n], FibElse[n, q]]#apply#asInstanceOfNat

    case class FibElse[n <: Nat, q <: nat.Peano](n: n, q: q) extends Function0 {
        type self = FibElse[n, q]
        override  def apply: apply = (fibonacci(n.decrement, q.decrement)  + fibonacci(n.decrement.decrement, q.decrement.decrement)).asInstanceOf[apply]
        override type apply        =  fibonacci[n#decrement, q.decrement]# +[fibonacci[n#decrement#decrement, q#decrement#decrement]]
    }
}


object FastFibonacci {
     def fibonacci[n <: Nat](n: n): fibonacci[n] = `if`(n  < _2,  Const0(n), Fib(_0, _1, n)).apply.asInstanceOfNat
    type fibonacci[n <: Nat]                     = `if`[n# <[_2], Const0[n], Fib[_0, _1, n]]#apply#asInstanceOfNat

    case class Fib[a <: Nat, b <: Nat, c <: Nat](a: a, b: b, c: c) extends Function0 {
        type self = Fib[a, b, c]
        override  def apply: apply = `if`(c.isZero, Const0(a), FibElse(a, b, c)).apply.asInstanceOfNat
        override type apply        = `if`[c#isZero, Const0[a], FibElse[a, b, c]]#apply#asInstanceOfNat
    }

    case class FibElse[a <: Nat, b <: Nat, c <: Nat](a: a, b: b, c: c) extends Function0 {
        type self = FibElse[a, b, c]
        override  def apply: apply = Fib(a  + b,  a, c.decrement).apply.asInstanceOf[apply]
        override type apply        = Fib[a# +[b], a, c#decrement]#apply
    }
}
