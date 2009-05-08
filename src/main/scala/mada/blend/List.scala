

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
                override def apply(_l: scala.List[Any]) = new Cons(_l.head.asInstanceOf[h], _typed(_l.tail))
            }
        }

    }


// List

    sealed trait List { // this: `this` =>
        private val _this = this.asInstanceOf[`this`]
        private[mada] type `this` <: List

        /**
         * Returns the first element.
         */
        def head: head
        type head

        /**
         * Returns the trailing list.
         */
        def tail: tail
        type tail <: List

        /**
         * Is this list nil?
         */
        final def isEmpty(implicit _unmeta: Meta.Unmeta[isEmpty, scala.Boolean]) = _unmeta()
        type isEmpty <: Meta.Boolean

        type append[l <: List] <: List

        /**
         * Returns the <code>i</code>-th element.
         */
        final def at[i <: Int](implicit _at: At[`this`, i#toNat]) = _at(_this)
        final type at[i <: Int] = metaAt[`this`, i#toNat]

        /**
         * Drops <code>i</code> elements.
         */
        final def drop[i <: Int](implicit _drop: Drop[`this`, i#toNat]) = _drop(_this)
        final type drop[i <: Int] = metaDrop[`this`, i#toNat]

        type take[i <: Int] <: List

        /**
         * Returns the length.
         */
        final def length(implicit _unmeta: Meta.Unmeta[length, scala.Int]) = _unmeta()
        type length <: Int

        /**
         * Converts to <code>scala.List[Any]</code>.
         */
        def untyped: scala.List[Any]

        /**
         * Prepends the element.
         */
        final def ::[A](e: A): Cons[A, `this`] = new Cons(e, _this)

        final override def toString = untyped.toString
    }


// Nil

    sealed trait Nil extends List {
        private[mada] override type `this` = Nil

        override def head = throw new NoSuchElementException("head of empty list")
        override type head = Meta.error
        override def tail = throw new NoSuchElementException("tail of empty list")
        override type tail = Nil // Note that meta-algorithms can't use `if`.
        override type isEmpty = Meta.`true`

        override type append[l <: List] = l
        override type take[i <: Int] = Nil
        override type length = Meta._0I

        override def untyped = scala.Nil
    }

    val Nil: Nil = new Nil{}

    // Compiler will fail to search implicits.
    // case object Nil; type Nil = Nil.type


// Cons

    final case class Cons[h, t <: List](override val head: h, override val tail: t) extends List {
        private[mada] override type `this` = Cons[h, t]

        override type head = h
        override type tail = t
        override type isEmpty = Meta.`false`

        override type length = tail#length#increment

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

    object Drop {
        implicit def ofNil[n <: Nat] = new Drop[Nil, n] {
            // * Compiler needs _l.tail due to metaDrop algorithm, but Nil.tail shouldn't return Nil.
            // * ofNilZero and ofNilSucc could remove asInstaceOf.
            override def apply(_l: Nil) = Nil.asInstanceOf[metaDrop[Nil, n]]
        }

        implicit def ofZero[h, t <: List] = new Drop[Cons[h, t], Nat.zero] {
            override def apply(_l: Cons[h, t]) = _l
        }

        implicit def ofSucc[h, t <: List, n <: Nat](implicit _drop: Drop[t, n]) = new Drop[Cons[h, t], Nat.succ[n]] {
            override def apply(_l: Cons[h, t]) = _drop(_l.tail)
        }
    }

}
