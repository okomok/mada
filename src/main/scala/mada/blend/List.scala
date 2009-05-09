

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.blend


// See: HList.scala
//      at http://www.assembla.com/wiki/show/metascala


import Meta.Nat


@provider
trait Lists { this: Blend.type =>


    object List {

        /**
         * Just like iterator of the meta List.
         */
        trait Visitor {
            type Result
            type visitNil <: Result
            type visitCons[h, t <: List] <: Result
        }

        /**
         * Creates meta List from <code>scala.List</code>.
         */
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
        type isEmpty <: Meta.Boolean
        final def isEmpty(implicit _unmeta: Meta.Unmeta[isEmpty, scala.Boolean]): scala.Boolean = _unmeta() // just for convenience.

        /**
         * Supports visitor iteration.
         */
        type accept[v <: List.Visitor] <: v#Result

        /**
         * Returns the <code>n</code>-th element.
         */
        final def at[n <: Nat](implicit _at: At[`this`, n]): at[n] = _at(_this)
        final type at[n <: Nat] = metaAt[`this`, n]

        /**
         * Drops EXACTLY <code>n</code> elements.
         */
        final def drop[n <: Nat](implicit _drop: Drop[`this`, n]): drop[n] = _drop(_this)
        final type drop[n <: Nat] = metaDrop[`this`, n]

        /**
         * Takes EXACTLY <code>n</code> elements.
         */
        final def take[n <: Nat](implicit _take: Take[`this`, n]): take[n] = _take(_this)
        final type take[n <: Nat] = metaTake[`this`, n]

        /**
         * @return <code>drop[n].take[m - n]</code>.
         */
        final def slice[n <: Nat, m <: Nat](implicit _slice: Slice[`this`, n, m]): slice[n, m] = _slice(_this)
        final type slice[n <: Nat, m <: Nat] = metaSlice[`this`, n, m]

        /**
         * Returns the last element.
         */
        final def last(implicit _last: Last[`this`, Meta.error]): last = _last(_this, Meta.nullOf[Meta.error])
        final type last = metaLast[`this`, Meta.error]

        /**
         * Returns the length.
         */
        final type length = metaLength[`this`]
        final def length(implicit _unmeta: Meta.Unmeta[length, scala.Int]): scala.Int = _unmeta() // just for convenience.

        /**
         * Prepends <code>that</code>.
         */
        final def :::[that <: List](_that: that)(implicit _prepend: Prepend[`this`, that]): prepend[that] = _prepend(_this, _that)
        final type prepend[that <: List] = metaPrepend[`this`, that]

        /**
         * Prepends reversed <code>that</code>.
         */
        final def reverse_:::[that <: List](_that: that)(implicit _reversePrepend: ReversePrepend[`this`, that]): reversePrepend[that] = _reversePrepend(_this, _that)
        final type reversePrepend[that <: List] = metaReversePrepend[`this`, that]

        /**
         * Returns reversed one.
         */
        final def reverse(implicit _reversePrepend: ReversePrepend[Nil, `this`]): reverse = _reversePrepend(Nil, _this)
        final type reverse = metaReversePrepend[Nil, `this`]

        /**
         * Converts to <code>scala.List[Any]</code>.
         */
        def untyped: untyped // The implicit way would annoy toString.
        final type untyped = scala.List[Any]

        /**
         * Prepends <code>e</code>.
         */
        final def ::[A](e: A): Cons[A, `this`] = Cons(e, _this)

        final override def toString = untyped.toString
    }

    /**
     * @return <code>r#prepend[l]</code>.
     */
    type :::[l <: List, r <: List] = r#prepend[l]

    /**
     * @return <code>r#reversePrepend[l]</code>.
     */
    type reverse_:::[l <: List, r <: List] = r#reversePrepend[l]


// Nil

    sealed trait Nil extends List {
        private[mada] override type `this` = Nil

        override def head = throw new NoSuchElementException("head of empty list")
        override type head = Meta.error
        override def tail = throw new NoSuchElementException("tail of empty list")
        override type tail = Meta.error // Nil would `List.take` less-restrictive, but less-mathematical.
        override type isEmpty = Meta.`true`
        override type accept[v <: List.Visitor] = v#visitNil

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
        override type accept[v <: List.Visitor] = v#visitCons[h, t]

        override def untyped = scala.::[Any](head, tail.untyped)
    }

    /**
     * Alias of <code>Cons</code>
     */
    type ::[h, t <: List] = Cons[h, t]


// at

    // drop#head can't replace this. The delayed #head destroys type identity.

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
        implicit def ofZero[l <: List] = new Drop[l, Nat.zero] {
            override def apply(_l: l) = _l
        }

        implicit def ofSucc[h, t <: List, n <: Nat](implicit _drop: Drop[t, n]) = new Drop[Cons[h, t], Nat.succ[n]] {
            override def apply(_l: Cons[h, t]) = _drop(_l.tail)
        }
    }


// take

    type metaTake[l <: List, n <: Nat] = n#accept[takeVisitor[l]]

    sealed trait takeVisitor[l <: List] extends Nat.Visitor {
        override type Result = List
        override type visitZero = Nil
        override type visitSucc[n <: Nat] = Cons[l#head, n#accept[takeVisitor[l#tail]]]
    }

    @specializer
    trait Take[l <: List, n <: Nat] extends (l => metaTake[l, n])

    object Take {
        implicit def ofZero[l <: List] = new Take[l, Nat.zero] {
            override def apply(_l: l) = Nil
        }

        implicit def ofSucc[h, t <: List, n <: Nat](implicit _take: Take[t, n]) = new Take[Cons[h, t], Nat.succ[n]] {
            override def apply(_l: Cons[h, t]) = Cons(_l.head, _take(_l.tail))
        }
    }


// slice

    type metaSlice[l <: List, n <: Nat, m <: Nat] = n#accept[sliceVisitor[l, m]]

    sealed trait sliceVisitor[l <: List, m <: Nat] extends Nat.Visitor {
        override type Result = List
        override type visitZero = metaTake[l, m]
        override type visitSucc[n <: Nat] = n#accept[sliceVisitor[l#tail, m#decrement]]
    }

    @specializer
    trait Slice[l <: List, n <: Nat, m <: Nat] extends (l => metaSlice[l, n, m])

    object Slice {
        implicit def ofZero[l <: List, m <: Nat](implicit _take: Take[l, m]) = new Slice[l, Nat.zero, m] {
            override def apply(_l: l) = _take(_l)
        }

        implicit def ofSucc[h, t <: List, n <: Nat, m <: Nat](implicit _slice: Slice[t, n, m]) = new Slice[Cons[h, t], Nat.succ[n], Nat.succ[m]] {
            override def apply(_l: Cons[h, t]) = _slice(_l.tail)
        }
    }


// last

    // lastOption might be better, but compiler can't find specializer for scala.None.type.

    type metaLast[l <: List, e] = l#accept[lastVisitor[e]]

    sealed trait lastVisitor[e] extends List.Visitor {
        override type Result = Any
        override type visitNil = e
        override type visitCons[h, t <: List] = t#accept[lastVisitor[h]]
    }

    // Synchronizing with metaLast algorithm can remove asInstanceOf.
    @specializer
    trait Last[l <: List, e] extends ((l, e) => metaLast[l, e])

    object Last {
        implicit def ofNil[e] = new Last[Nil, e] {
            override def apply(_l: Nil, _e: e) = _e
        }

        implicit def ofCons[h, t <: List, e](implicit _last: Last[t, h]) = new Last[Cons[h, t], e] {
            override def apply(_l: Cons[h, t], unused: e) = _last(_l.tail, _l.head)
        }
    }


// init

    // I don't know the 1-path algorithm.
    // "#take with #length#decrement" doesn't work: #length destroys type identity,
    // so that compiler fails to find specializers.


// length

    type metaLength[l <: List] = l#accept[lengthVisitor[Nat.zero]]

    sealed trait lengthVisitor[n <: Nat] extends List.Visitor {
        override type Result = Meta.Nat
        override type visitNil = n
        override type visitCons[h, t <: List] = t#accept[lengthVisitor[n#increment]]
    }


// prepend

    type metaPrepend[r <: List, l <: List] = l#accept[prependVisitor[r]]

    sealed trait prependVisitor[r <: List] extends List.Visitor {
        override type Result = List
        override type visitNil = r
        override type visitCons[h, t <: List] = Cons[h, t#accept[prependVisitor[r]]]
    }

    @specializer
    trait Prepend[r <: List, l <: List] extends ((r, l) => metaPrepend[r, l])

    object Prepend {
        implicit def ofNil[r <: List] = new Prepend[r, Nil] {
            override def apply(_r: r, _l: Nil) = _r
        }

        implicit def ofCons[r <: List, h, t <: List](implicit _prepend: Prepend[r, t]) = new Prepend[r, Cons[h, t]] {
            override def apply(_r: r, _l: Cons[h, t]) = Cons(_l.head, _prepend(_r, _l.tail))
        }
    }


// reversePrepend

    type metaReversePrepend[r <: List, l <: List] = l#accept[reversePrependVisitor[r]]

    sealed trait reversePrependVisitor[r <: List] extends List.Visitor {
        override type Result = List
        override type visitNil = r
        override type visitCons[h, t <: List] = t#accept[reversePrependVisitor[Cons[h, r]]]
    }

    @specializer
    trait ReversePrepend[r <: List, l <: List] extends ((r, l) => metaReversePrepend[r, l])

    object ReversePrepend {
        implicit def ofNil[r <: List] = new ReversePrepend[r, Nil] {
            override def apply(_r: r, _l: Nil) = _r
        }

        implicit def ofCons[r <: List, h, t <: List](implicit _reversePrepend: ReversePrepend[Cons[h, r], t]) = new ReversePrepend[r, Cons[h, t]] {
            override def apply(_r: r, _l: Cons[h, t]) = _reversePrepend(Cons(_l.head, _r), _l.tail)
        }
    }

}
