

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual; package list


// cf. take(n) = reverse.drop(size - n).reverse
/*

private[mada] object Take {
     def apply[xs <: List, n <: nat.Peano](xs: xs, n: n): apply[xs, n] = if_List(new Cond(xs, n).apply, new always0_Nil, new Else(xs, n)).apply
    type apply[xs <: List, n <: nat.Peano] = if_List[Cond[xs, n]#apply, always0_Nil, Else[xs, n]]#apply

    @compilerWorkaround("2.8.0") // works around a type mismatch.
    class Cond[xs <: List, n <: nat.Peano](xs: xs, n: n) extends Function0_Boolean {
        override  def apply: apply = xs.isEmpty || n === nat.peano._0
        override type apply = xs#isEmpty# ||[n# ===[nat.peano._0]]
    }

    class Else[xs <: List, n <: nat.Peano](xs: xs, n: n) extends Function0_List {
        override  def apply: apply = Cons(xs.head, Take.apply(xs.tail, n.decrement))
        override type apply = Cons[xs#head, Take.apply[xs#tail, n#decrement]]
    }
}
*/
