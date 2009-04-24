

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


// toy


object Hetero {

    type ::[H, T <: Iterator] = ConsIterator[H, T]


    sealed trait Iterator {
        type Head
        type ToNext <: Iterator
        type HasNext <: Meta.Boolean

        def head: Head
        def toNext: ToNext
    }


    final class NilIterator extends Iterator {
        override type Head = Nothing
        override type ToNext = Nothing
        override type HasNext = Meta.`false`

        def head: Head = throw new Error
        def toNext: ToNext = throw new Error

        def ::[A](e: A): ConsIterator[A, NilIterator] = ConsIterator(e, this)
    }

    val NilIterator = new NilIterator


    final case class ConsIterator[H, T <: Iterator](head: H, toNext: T) extends Iterator {
        override type Head = H
        override type ToNext = T
        override type HasNext = Meta.`true`

        def ::[A](e: A): ConsIterator[A, ConsIterator[H, T]] = ConsIterator(e, this)
    }


    trait PolyFunction1 {
        type Apply[A]
        def apply[A](e: A): Any
    }

    class MapIterator[I <: Iterator, F <: PolyFunction1](it: I, f: F) extends Iterator {
        override type Head = F#Apply[I#Head]
        override type ToNext = MapIterator[I#ToNext, F]
        override type HasNext = I#HasNext

        def head: Head = f(it.head).asInstanceOf[Head]
        def toNext: ToNext = new MapIterator(it.toNext, f)
    }


    object Iterable {
        def by[I <: Iterator](it: I): Iterable[I] = new Iterable[I] {
            override val elements = it
        }

        implicit def fromProduct2[T1, T2](from: Product2[T1, T2]): Iterable[T1::T2::NilIterator] = by(from._1::from._2::NilIterator)

        /**
         * Triggers implicit conversions explicitly.
         *
         * @return  <code>to</code>.
         */
        def from[T1, T2](to: Iterable[T1::T2::NilIterator]) = to
    }

    trait Iterable[I <: Iterator] {
        type Elements = I
        def elements: I

        def map[F <: PolyFunction1](f: F) = Iterable.by(new MapIterator(elements, f))
    }



    class MyTuple(_1: Int, _2: String) extends Iterable[Int::String::NilIterator] {
        def elements = _1::_2::NilIterator
    }

}

