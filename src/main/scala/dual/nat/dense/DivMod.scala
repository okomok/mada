

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package nat; package dense


private[mada] final class DivMod {
     def apply[x <: Dense, y <: Dense](x: x, y: y): apply[x, y] =
        `if`(y.size  < x.size,  Then(x, y), Else(x, y)).apply.asInstanceOfProduct2.asInstanceOf[apply[x, y]]
    type apply[x <: Dense, y <: Dense] =
        `if`[y#size# <[x#size], Then[x, y], Else[x, y]]#apply#asInstanceOfProduct2

    case class Then[x <: Dense, y <: Dense](x: x, y: y) extends Function0 {
        override  val self = this
        override type self = Then[x, y]

        lazy val count2: count2 = x.size - y.size
        type count2 = x#size# -[y#size]

        lazy val count1: count1 = count2.decrement
        type count1 = count2#decrement

        lazy val canMinus1: canMinus1 = y.shiftLeftBy(count1)
        type canMinus1 = y#shiftLeftBy[count1]

        lazy val canMinus2: canMinus2 = canMinus1.shiftLeft
        type canMinus2 = canMinus1.shiftLeft

        lazy val quot1: quot1 = _1.shiftLeftBy(count1)
        type quot1 = _1#shiftLeftBy[count1]

        lazy val quot2: quot2 = _1.shiftLeftBy(count2)
        type quot2 = _1#shiftLeftBy[count2]

        override  def apply: apply =
            `if`(canMinus2  <= x,  Next(x, y, quot2, canMinus2), Next(x, y, quot1, canMinus1)).apply.asInstanceOf[apply]
        override type apply =
            `if`[canMinus2# <=[x], Next[x, y, quot2, canMinus2], Next[x, y, quot1, canMinus1]]#apply
    }

    case class Next[x <: Dense, y <: Dense, quot <: Dense, canMinus <: Dense](x: x, y: y, quot: quot, canMinus: canMinus) extends Function0 {
        override  val self = this
        override type self = Next[x, y, quot, canMinus]

        lazy val r: r = (x  - canMinus).divMod(y)
        type r        =  x# -[canMinus]#divMod[y]

        override  def apply: apply = Tuple2(quot + r._1.asInstanceOfNat, r._2)
        override type apply = Tuple2[quot# +[r._1#asInstanceOfNat], r#_2]
    }

    case class Else[x <: Dense, y <: Dense](x: x, y: y) extends Function0 {
        override  val self = this
        override type self = Else[x, y]
        override  def apply: apply = `if`(x  < y,  const0(Tuple2(Nil, x)), ElseElse(x, y)).apply
        override type apply        = `if`[x# <[y], const0[Tuple2[Nil, x]], ElseElse[x, y]]#apply
    }

    case class ElseElse[x <: Dense, y <: Dense](x: x, y: y) extends Function0 {
        override  val self = this
        override type self = ElseElse[x, y]
        override  def apply: apply = Tuple2(_1, x - y)
        override type apply        = Tuple2[_1, x# -[y]]
    }
}


private[mada] final class ConsShiftLeftBy {
     def apply[x <: Dense, n <: Peano](x: x, n: n): apply[x, n] = n.foldRight(x, Step()).asInstanceOfNatDense
    type apply[x <: Dense, n <: Peano] = n#foldRight[x, Step]#asInstanceOfNatDense

    case class Step() extends Function2 {
        override  val self = this
        override type self = Step
        override  def apply[a <: Any, b <: Any](a: a, b: b): apply[a, b] = Cons(`false`, b.asInstanceOfNatDense)
        override type apply[a <: Any, b <: Any] = Cons[`false`, b#asInstanceOfNatDense]
    }
}
