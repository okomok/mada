

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
        type isNil <: Meta.Boolean

        type append[l <: List] <: List
        type at[i <: Int]
        type drop[i <: Int] <: List
        type take[i <: Int] <: List

        type length <: Int

        /**
         * Returns the length.
         */
        def length: scala.Int

        /**
         * Converts to <code>scala.List[Any]</code>.
         */
        def typeErase: scala.List[Any]

        override def toString = typeErase.toString
    }


// Nil

    case object Nil extends List {
        override type head = Meta.error
        override type tail = Nil
        override type isNil = Meta.`true`
        def ::[A](e: A) = Cons(e, this)

        override type append[l <: List] = l
        override type at[i <: Int] = Meta.error
        override type drop[i <: Int] = Nil
        override type take[i <: Int] = Nil
        override type length = Meta._0I

        override def length = 0
        override def typeErase = scala.Nil
    }

    type Nil = Nil.type


// Cons

    final case class Cons[h, t <: List](head: h, tail: t) extends List {
        type `this` = Cons[h, t]
        override type head = h
        override type tail = t
        override type isNil = Meta.`false`
        def ::[A](e: A) = Cons(e, this)

        override type at[i <: Int] = metaAt[`this`, i#toNat]
        def at[i <: Int](implicit _at: At[`this`, i#toNat]) = _at(this)

        override type drop[i <: Int] = metaDrop[`this`, i#toNat]
        def drop[i <: Int](implicit _drop: Drop[`this`, i#toNat]) = _drop(this)

        override type length = t#length#increment
        override def length = tail.length + 1 // length(implicit _unmeta: Meta.Unmeta[length, scala.Int]) = _unmeta()

        override def typeErase = scala.::[Any](head, tail.typeErase)
    }

    type ::[h, t <: List] = Cons[h, t]


// at

    type metaAt[l <: List, n <: Nat] = n#accept[atVisitor[l]]

    sealed trait atVisitor[l <: List] extends Nat.Visitor {
        override type Result = Any
        override type visitZero = l#head
        override type visitSucc[n <: Nat] = n#accept[atVisitor[l#tail]]
    }

    @specializer
    trait At[l <: List, n <: Nat] extends (l => metaAt[l, n])

    object At {
        implicit def ofZero[h, t <: List] = new At[Cons[h, t], Nat.zero] {
            override def apply(_l: Cons[h, t]) = _l.head
        }

        implicit def ofSucc[h, t <: List, n <: Nat](implicit _at: At[t, n]) = new At[Cons[h, t], Nat.succ[n]] {
            override def apply(_l: Cons[h, t]) = _at(_l.tail)
        }
    }


// drop

    type metaDrop[l <: List, n <: Nat] = n#accept[dropVisitor[l]]

    sealed trait dropVisitor[l <: List] extends Nat.Visitor {
        override type Result = List
        override type visitZero = l
        override type visitSucc[n <: Nat] = n#accept[dropVisitor[l#tail]]
    }

    @specializer
    trait Drop[l <: List, n <: Nat] extends (l => metaDrop[l, n])

    object Drop {
        implicit def ofZero[h, t <: List] = new Drop[Cons[h, t], Nat.zero] {
            override def apply(_l: Cons[h, t]) = _l
        }

        implicit def ofSucc[h, t <: List, n <: Nat](implicit _drop: Drop[t, n]) = new Drop[Cons[h, t], Nat.succ[n]] {
            override def apply(_l: Cons[h, t]) = _drop(_l.tail)
        }
    }

}
