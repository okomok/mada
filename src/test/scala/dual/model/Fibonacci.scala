

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package dualtest; package modeltest


import com.github.okomok.mada
import mada.dual._
import mada.dual.nat.dense.Literal._
import mada.dual.nat.dense.{Nil, ::, _1B, _0B}


object FastFibonacci {
     def fibonacci[n <: Nat](n: n): fibonacci[n] = `if`(n  < _2,  const0(n), FibElse(n)).apply.asInstanceOfNat
    type fibonacci[n <: Nat]                     = `if`[n# <[_2], const0[n], FibElse[n]]#apply#asInstanceOfNat

    case class FibElse[n <: Nat](n: n) extends Function0 {
        type self = FibElse[n]
        override  def apply: apply = (fibonacci(n.decrement)  + fibonacci(n.decrement.decrement)).asInstanceOf[apply]
        override type apply        =  fibonacci[n#decrement]# +[fibonacci[n#decrement#decrement]]
    }
}

object SlowFibonacci {
     def fibonacci[n <: Nat](n: n): fibonacci[n] = `if`(n  < _2,  const0(n), FibElse(n)).apply.asInstanceOfNat
    type fibonacci[n <: Nat]                     = `if`[n# <[_2], const0[n], FibElse[n]]#apply#asInstanceOfNat

    case class FibElse[n <: Nat](n: n) extends Function0 {
        type self = FibElse[n]
        // Now child-node type-expressions are different.
        override  def apply: apply = (fibonacci(n  - _1)   + fibonacci(n.decrement.decrement)).asInstanceOf[apply]
        override type apply        =  fibonacci[n# -[_1]]# +[fibonacci[n#decrement#decrement]]
    }
}


object FastFibonacci2 {
     def fibonacci[n <: Nat](n: n): fibonacci[n] = new _FastFibonacci2(n).apply
    type fibonacci[n <: Nat]                     =     _FastFibonacci2[n]#apply

}

class _FastFibonacci2[n <: Nat](n: n) {
     def apply: apply = `if`(n  < _2,  const0(n), new Fib2Else(n)).apply.asInstanceOfNat
    type apply        = `if`[n# <[_2], const0[n],     Fib2Else[n]]#apply#asInstanceOfNat
}

    class Fib2Else[n <: Nat](n: n) extends Function0 {
        type self = Fib2Else[n]
        override  def apply: apply = (new _FastFibonacci2(n.decrement).apply  + new _FastFibonacci2(n.decrement.decrement).apply).asInstanceOf[apply]
        override type apply        =      _FastFibonacci2[n#decrement]#apply# +    [_FastFibonacci2[n#decrement#decrement]#apply]
    }


object SlowFibonacci2 {
     def fibonacci[n <: Nat](n: n): fibonacci[n] = new _SlowFibonacci2(n).apply
    type fibonacci[n <: Nat]                     =     _SlowFibonacci2[n]#apply
}

class _SlowFibonacci2[n <: Nat](n: n) {
     def apply: apply = `if`(n  < _2,  const0(n), new FibElse(n)).apply.asInstanceOfNat
    type apply        = `if`[n# <[_2], const0[n],     FibElse[n]]#apply#asInstanceOfNat

    class FibElse[n <: Nat](n: n) extends Function0 {
        type self = FibElse[n]
        override  def apply: apply = (new _SlowFibonacci2(n.decrement).apply  + new _SlowFibonacci2(n.decrement.decrement).apply).asInstanceOf[apply]
        override type apply        =      _SlowFibonacci2[n#decrement]#apply# +    [_SlowFibonacci2[n#decrement#decrement]#apply]
    }
}


object FastFibonacci3 {
     def fibonacci[n <: Nat](n: n): fibonacci[n] = new _FastFibonacci3().apply(n)
    type fibonacci[n <: Nat]                     =     _FastFibonacci3#apply[n]

}

class _FastFibonacci3 {
     def apply[n <: Nat](n: n): apply[n] = `if`(n  < _2,  const0(n), new FibElse(n)).apply.asInstanceOfNat
    type apply[n <: Nat]                 = `if`[n# <[_2], const0[n],     FibElse[n]]#apply#asInstanceOfNat

    class FibElse[n <: Nat](n: n) extends Function0 {
        type self = FibElse[n]
        override  def apply: apply = (new _FastFibonacci3().apply(n.decrement)  + new _FastFibonacci3().apply(n.decrement.decrement)).asInstanceOf[apply]
        override type apply        =      _FastFibonacci3#apply[n#decrement]# +    [_FastFibonacci3#apply[n#decrement#decrement]]
    }
}


object SlowFibonacci4 {
     def fibonacci[n <: Nat](n: n): fibonacci[n] = new _SlowFibonacci4(n).apply(n)
    type fibonacci[n <: Nat]                     =     _SlowFibonacci4[n]#apply[n]

}

class _SlowFibonacci4[m <: Nat](m: m) {
     def apply[n <: Nat](n: n): apply[n] = `if`(n  < _2,  const0(n), new FibElse(n)).apply.asInstanceOfNat
    type apply[n <: Nat]                 = `if`[n# <[_2], const0[n],     FibElse[n]]#apply#asInstanceOfNat

    class FibElse[n <: Nat](n: n) extends Function0 {
        type self = FibElse[n]
        override  def apply: apply = (new _SlowFibonacci4(m).apply(n.decrement)  + new _SlowFibonacci4(m).apply(n.decrement.decrement)).asInstanceOf[apply]
        override type apply        =      _SlowFibonacci4[m]#apply[n#decrement]# +    [_SlowFibonacci4[m]#apply[n#decrement#decrement]]
    }
}


object SlowFibonacci5 {
     def fibonacci[n <: Nat](n: n): fibonacci[n] = new _SlowFibonacci5().apply(n)
    type fibonacci[n <: Nat]                     =     _SlowFibonacci5#apply[n]

    class _SlowFibonacci5 {
         def apply[n <: Nat](n: n): apply[n] = `if`(n  < _2,  const0(n), new FibElse(n)).apply.asInstanceOfNat
        type apply[n <: Nat]                 = `if`[n# <[_2], const0[n],     FibElse[n]]#apply#asInstanceOfNat

        class FibElse[n <: Nat](n: n) extends Function0 {
            type self = FibElse[n]
            override  def apply: apply = (new _SlowFibonacci5().apply(n.decrement)  + new _SlowFibonacci5().apply(n.decrement.decrement)).asInstanceOf[apply]
            override type apply        =      _SlowFibonacci5#apply[n#decrement]# +    [_SlowFibonacci5#apply[n#decrement#decrement]]
        }
    }
}
