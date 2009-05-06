

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.blend


@provider
trait Lists { this: Blend.type =>


// List

    sealed trait List {
        type head
        type tail <: List
    }

    final class Nil extends List {
        override type head = Meta.error
        override type tail = Meta.error

        def ::[A](e: A) = Cons(e, this)
    }

    val Nil = new Nil

    final case class Cons[h, t <: List](head: h, tail: t) extends List {
        type `this` = Cons[h, t]

        override type head = h
        override type tail = t

        def ::[A](e: A) = Cons(e, this)

        type at[i <: Meta.Int] = At.apply[`this`, i#toNat]
        def at[i <: Meta.Int](implicit _at: At[`this`, i#toNat]) = At.apply[`this`, i#toNat](this, _at)
    }

    type ::[h, t <: List] = Cons[h, t]


// at

    @specializer
    trait At[l <: List, n <: Meta.Nat] {
        def apply(_l: l): At.apply[l, n]
    }

    object At {
        type apply[l <: List, n <: Meta.Nat] = n#accept[visitor[l]]
        def apply[l <: List, n <: Meta.Nat](_l: l, _at: At[l, n]) = _at(_l)

        sealed trait visitor[l <: List] extends Meta.Nat.Visitor {
            override type Result = Any
            override type visitZero = l#head
            override type visitSucc[n <: Meta.Nat] = n#accept[visitor[l#tail]]
        }

        implicit def at_0[h, t <: List] = new At[Cons[h, t], Meta.Nat.zero] {
            override def apply(_l: Cons[h, t]) = _l.head
        }

        implicit def at_N[h, t <: List, n <: Meta.Nat](implicit _at: At[t, n]) = new At[Cons[h, t], Meta.Nat.succ[n]] {
            override def apply(_l: Cons[h, t]) = _at(_l.tail)
        }

    }

}
