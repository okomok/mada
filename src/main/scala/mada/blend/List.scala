

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.blend


import Meta.{ Int, Nat }


@provider
trait Lists { this: Blend.type =>


// List

    sealed trait List {
        type head
        type tail <: List

        type append[l <: List] <: List
        type at[i <: Int]
        type drop[i <: Int] <: List
        type take[i <: Int] <: List
        type length <: Int
    }

    final class Nil extends List {
        override type head = Meta.error
        override type tail = Meta.error
        def ::[A](e: A) = Cons(e, this)

        override type append[l <: List] = l
        override type at[i <: Int] = Meta.error
        override type drop[i <: Int] = Nil
        override type take[i <: Int] = Nil
        override type length = Meta._0I
    }

    val Nil = new Nil

    final case class Cons[h, t <: List](head: h, tail: t) extends List {
        type `this` = Cons[h, t]
        override type head = h
        override type tail = t
        def ::[A](e: A) = Cons(e, this)

        override type at[i <: Int] = At.apply[`this`, i#toNat]
        def at[i <: Int](implicit _at: At[`this`, i#toNat]) = At.apply[`this`, i#toNat](this, _at)

        override type drop[i <: Int] = Drop.apply[`this`, i#toNat]
        def drop[i <: Int](implicit _drop: Drop[`this`, i#toNat]) = Drop.apply[`this`, i#toNat](this, _drop)

        override type length = t#length#increment
        def length(implicit _unmeta: Meta.Unmeta[length, scala.Int]): scala.Int = Meta.unmeta[length, scala.Int](_unmeta)
    }

    type ::[h, t <: List] = Cons[h, t]


// at

    @specializer
    trait At[l <: List, n <: Nat] extends (l => At.apply[l, n])

    object At {
        type apply[l <: List, n <: Nat] = n#accept[visitor[l]]

        sealed trait visitor[l <: List] extends Nat.Visitor {
            override type Result = Any
            override type visitZero = l#head
            override type visitSucc[n <: Nat] = n#accept[visitor[l#tail]]
        }

        def apply[l <: List, n <: Nat](_l: l, _at: At[l, n]) = _at(_l)

        implicit def ofZero[h, t <: List] = new At[Cons[h, t], Nat.zero] {
            override def apply(_l: Cons[h, t]) = _l.head
        }

        implicit def ofSucc[h, t <: List, n <: Nat](implicit _at: At[t, n]) = new At[Cons[h, t], Nat.succ[n]] {
            override def apply(_l: Cons[h, t]) = _at(_l.tail)
        }
    }


// drop

    @specializer
    trait Drop[l <: List, n <: Nat] extends (l => Drop.apply[l, n])

    object Drop {
        type apply[l <: List, n <: Nat] = n#accept[visitor[l]]

        sealed trait visitor[l <: List] extends Nat.Visitor {
            override type Result = List
            override type visitZero = l
            override type visitSucc[n <: Nat] = n#accept[visitor[l#tail]]
        }

        def apply[l <: List, n <: Nat](_l: l, _drop: Drop[l, n]) = _drop(_l)

        implicit def ofZero[h, t <: List] = new Drop[Cons[h, t], Nat.zero] {
            override def apply(_l: Cons[h, t]) = _l
        }

        implicit def ofSucc[h, t <: List, n <: Nat](implicit _drop: Drop[t, n]) = new Drop[Cons[h, t], Nat.succ[n]] {
            override def apply(_l: Cons[h, t]) = _drop(_l.tail)
        }
    }
}
