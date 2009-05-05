

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.blend


import Meta.Int


@provider
trait Lists { this: Blend.type =>
/*

    sealed trait List extends Meta.Object {
        type head
        type tail <: List
    }


    final class Nil extends List {
        override type head = Nothing
        override type tail = Nil

        def ::[A](e: A) = Cons(e, this)
    }


    final case class Cons[H, T <: List](head: H, tail: T) extends List {
        type `this` = Cons[H, T]
        override type head = H
        override type tail = T

        def head_ : head = head

        def ::[A](e: A) = Cons(e, this)

        def at[n <: Int](implicit _at: At[`this`, n]): at[`this`, n] = _at(this)
    }

    type tailOf[l <: List, _] = l#tail
    type tailFn = Meta.quote2[tailOf, List, Int, List]
    type at[l <: List, n <: Int] = n#foldLeft[l, tailFn]

    Meta.assert[ at[Cons[Meta._3I, Cons[Meta._4I, Cons[Meta._5I, Nil]]], Meta._1I]#head #equals [Meta._4I]]
    Meta.assertSame[ at[Cons[Meta._3I, Cons[Meta._4I, Cons[Meta._5I, Nil]]], Meta._1I]#head, Meta._4I]

    Meta.assert[ at[Cons[Meta._3I, Cons[Meta._4I, Cons[Meta._5I, Nil]]], Meta._0I]#head #equals [Meta._3I]]
    Meta.assertSame[ at[Cons[Meta._3I, Cons[Meta._4I, Cons[Meta._5I, Nil]]], Meta._0I]#head, Meta._3I]


    trait At[l <: List, n <: Int] extends Specializer {
   //     type R <: at[l, n]
        def apply(_l: l)(implicit _at: At[l#tail, n#decrement]): at[l, n]
    }

    object At {
        implicit def at_0[H, T <: List] = new At[Cons[H, T], Meta._0I] {
      //      override type R = at[Cons[H, T], Meta._0I]
            override def apply(_l: Cons[H, T])(implicit _at: At[T, Meta._0I#decrement]) = _l
        }

        implicit def at_N[H, T <: List, n <: Int] = new At[Cons[H, T], n] {
       //     override type R = at[Cons[H, T], n]
            override def apply(_l: Cons[H, T])(implicit _at: At[T, n#decrement]) = _at(_l.tail)
        }

    }
*/
}
