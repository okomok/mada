

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.blend


import Meta.{ Int, Nat }


@provider
trait Lists { this: Blend.type =>


    object List {

        def typed[l <: List](from: scala.List[Any])(implicit _typed: Typed[l]) = _typed(from)

        @specializer
        trait Typed[l <: List] extends (scala.List[Any] => l)

        object Typed {
            implicit val ofNil = new Typed[Nil] {
                override def apply(_l: scala.List[Any]) = Nil
            }

            implicit def ofCons[h, t <: List](implicit _typed: Typed[t]) = new Typed[Cons[h, t]] {
                override def apply(_l: scala.List[Any]) = Cons(_l.head.asInstanceOf[h], _typed(_l.tail))
            }
        }

    }


// List

    sealed trait List {
        type `this` <: List

        /**
         * Is this list nil?
         */
        def isEmpty: scala.Boolean
        type isEmpty <: Meta.Boolean

        type head
        type tail <: List

        type append[l <: List] <: List
        type at[i <: Int]
        type drop[i <: Int] <: List
        type take[i <: Int] <: List

        /**
         * Returns the length.
         */
        def length: scala.Int
        type length <: Int

        /**
         * Converts to <code>scala.List[Any]</code>.
         */
        def untyped: scala.List[Any]

        override def toString = untyped.toString

        /**
         * Prepends the element.
         */
        final def ::[A](e: A): Cons[A, `this`] = new Cons(e, this.asInstanceOf[`this`])
    }


// Nil

    sealed trait Nil extends List {
        override type `this` = Nil

        override def isEmpty = true
        override type isEmpty = Meta.`true`

        override type head = Meta.error
        override type tail = Nil // Note that meta-algorithms can't use `if`.

        override type append[l <: List] = l
        override type at[i <: Int] = Meta.error
        override type drop[i <: Int] = Nil
        override type take[i <: Int] = Nil

        override def length = 0
        override type length = Meta._0I

        override def untyped = scala.Nil
    }

    val Nil = new Nil{}

    // Compiler will fails to find implicits.
    // case object Nil; type Nil = Nil.type


// Cons

    final case class Cons[h, t <: List](head: h, tail: t) extends List {
        type `this` = Cons[h, t]

        override def isEmpty = false
        override type isEmpty = Meta.`false`

        override type head = h
        override type tail = t

        def at[i <: Int](implicit _at: At[`this`, i#toNat]) = _at(this)
        override type at[i <: Int] = metaAt[`this`, i#toNat]

        def drop[i <: Int](implicit _drop: Drop[`this`, i#toNat]) = _drop(this)
        override type drop[i <: Int] = metaDrop[`this`, i#toNat]

        override def length = tail.length + 1
        override type length = t#length#increment

        override def untyped = scala.::[Any](head, tail.untyped)
    }

    /**
     * Alias of <code>Cons</code>
     */
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

    object Drop { // Follows metaDrop algorithm to avoid asInstanceOf.
        implicit val ofNilZero = new Drop[Nil, Nat.zero] {
            override def apply(_l: Nil) = _l
        }

        implicit def ofNilSucc[n <: Nat](implicit _drop: Drop[Nil, n]) = new Drop[Nil, Nat.succ[n]] {
            override def apply(_l: Nil) = _drop(_l)
        }

        implicit def ofConsZero[h, t <: List] = new Drop[Cons[h, t], Nat.zero] {
            override def apply(_l: Cons[h, t]) = _l
        }

        implicit def ofConsSucc[h, t <: List, n <: Nat](implicit _drop: Drop[t, n]) = new Drop[Cons[h, t], Nat.succ[n]] {
            override def apply(_l: Cons[h, t]) = _drop(_l.tail)
        }
    }

}
