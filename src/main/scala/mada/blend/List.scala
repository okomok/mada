

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

            implicit val of0 = new Typed[Nil] {
                override def apply(_l: scala.List[Any]) = Nil
            }

            implicit def of1[h1] = new Typed[Cons[h1, Nil]] {
                override def apply(_l: scala.List[Any]) = Cons(_l.head.asInstanceOf[h1], of0(_l.tail))
            }

            implicit def of2[h1, h2] = new Typed[Cons[h1, Cons[h2, Nil]]] {
                override def apply(_l: scala.List[Any]) = Cons(_l.head.asInstanceOf[h1], of1[h2](_l.tail))
            }

            implicit def of3[h1, h2, h3] = new Typed[Cons[h1, Cons[h2, Cons[h3, Nil]]]] {
                override def apply(_l: scala.List[Any]) = Cons(_l.head.asInstanceOf[h1], of2[h2, h3](_l.tail))
            }

            implicit def of4[h1, h2, h3, h4] = new Typed[Cons[h1, Cons[h2, Cons[h3, Cons[h4, Nil]]]]] {
                override def apply(_l: scala.List[Any]) = Cons(_l.head.asInstanceOf[h1], of3[h2, h3, h4](_l.tail))
            }
    /*
            // Compilation doesn't terminate...
            implicit def ofCons[h, t <: List](implicit _typed: Typed[t]) = new Typed[Cons[h, t]] {
                override def apply(_l: scala.List[Any]) = Cons(_l.head.asInstanceOf[h], _typed(_l.tail))
            }
    */
        }

    }


// List

    sealed trait List {
        type `this` <: List
        type isNil <: Meta.Boolean

        type head
        type tail <: List

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
        def untyped: scala.List[Any]

        override def toString = untyped.toString
    }


// Nil

    case object Nil extends List {
        override type `this` = List
        override type isNil = Meta.`true`

        override type head = Meta.error
        override type tail = Nil//Meta.error//Nil
        def ::[A](e: A) = Cons(e, this)

        override type append[l <: List] = l
        override type at[i <: Int] = Meta.error
        override type drop[i <: Int] = Nil
        override type take[i <: Int] = Nil
        override type length = Meta._0I

        override def length = 0
        override def untyped = scala.Nil
    }

    type Nil = Nil.type


// Cons

    final case class Cons[h, t <: List](head: h, tail: t) extends List {
        type `this` = Cons[h, t]
        override type isNil = Meta.`false`

        override type head = h
        override type tail = t
        def ::[A](e: A) = Cons(e, this)

        override type at[i <: Int] = metaAt[`this`, i#toNat]
        def at[i <: Int](implicit _at: At[`this`, i#toNat]) = _at(this)

        override type drop[i <: Int] = metaDrop[`this`, i#toNat]
//        def drop[i <: Int](implicit _drop: Drop[`this`, i#toNat]) = _drop(this)
//        def drop[i <: Int](implicit _unmeta: Meta.Unmeta[i, scala.Int], _typed: List.Typed[drop[i]]) = _typed(untyped.drop(_unmeta()))

        override type length = t#length#increment
        override def length = tail.length + 1 // length(implicit _unmeta: Meta.Unmeta[length, scala.Int]) = _unmeta()

        override def untyped = scala.::[Any](head, tail.untyped)
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
    trait Drop[l <: List, n <: Nat] extends (l => metaDrop[l, n]) {
    //    type Argument = l
    }

    @specializer
    trait DropNil[n <: Nat] {
        def apply: Nil
    }

    object Drop {
/*
        implicit def ofNil[n <: Nat] = new Drop[Nil, n] {
            override def apply(_l: Nil) = _l//{ Assert(_l eq Nil); _l.asInstanceOf[metaDrop[Nil, n]] }
        }

        implicit val ofNilZero = new Drop[Nil, Nat.zero] {
            override def apply(_l: Nil) = _l //{ Assert(_l eq Nil); _l.asInstanceOf[metaDrop[Nil, Nat.zero]] }
        }

        implicit def ofNilSucc[n <: Nat](implicit _drop: Drop[Nil, n]) = new Drop[Nil, Nat.succ[n]] {
            override def apply(_l: Nil) = _drop(_l)//{ Assert(_l eq Nil); _l.asInstanceOf[metaDrop[Nil, Nat.succ[n]]] }
        }

        implicit def ofLastZero[h] = new Drop[Cons[h, Nil], Nat.zero] {
            override def apply(_l: Cons[h, Nil]) = _l
        }

        implicit def ofLastSucc[h, n <: Nat] = new Drop[Cons[h, Nil], Nat.succ[n]] {
            override def apply(_l: Cons[h, Nil]) = _l.asInstanceOf[metaDrop[Cons[h, Nil], Nat.succ[n]]]
        }

        implicit def ofZero[h, t <: List] = new Drop[Cons[h, t], Nat.zero] {
            override def apply(_l: Cons[h, t]) = _l
        }

        implicit def ofSucc[h, t <: List, n <: Nat](implicit _drop: Drop[t, n]) = new Drop[Cons[h, t], Nat.succ[n]] {
            override def apply(_l: Cons[h, t]) = _drop(_l.tail)
        }

        implicit def ofNil[n <: Nat] = new Drop[Nil, n] {
            override def apply(_l: Nil) = { Assert(_l eq Nil); _l.asInstanceOf[metaDrop[Nil, n]] }
        }

        implicit def ofZero[h, t <: List] = new Drop[Cons[h, t], Nat.zero] {
            override def apply(_l: Cons[h, t]) = _l
        }

        implicit def ofSucc[h, t <: List, n <: Nat](implicit _drop: Drop[t, n]) = new Drop[Cons[h, t], Nat.succ[n]] {
            override def apply(_l: Cons[h, t]) = _drop(_l.tail)
        }

        implicit def ofLastSucc[h, n <: Nat] = new Drop[Cons[h, Nil], Nat.succ[n]] {
            override def apply(_l: Cons[h, Nil]) = _l.tail
        }
*/
    }

}
