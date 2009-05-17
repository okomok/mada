

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


@compatibles
trait Compatibles { self: Traversable.type =>
    implicit def madaTraversableFromSIterable[A](from: Iterable[A]): Traversable[A] = fromSIterable(from)
    implicit def madaTraversableToSIterable[A](from: Traversable[A]): Iterable[A] = from.toSIterable
    implicit def madaTraversableFromJIterable[A](from: java.lang.Iterable[A]): Traversable[A] = fromJIterable(from)
    implicit def madaTraversableToJIterable[A](from: Traversable[A]): java.lang.Iterable[A] = from.toJIterable
    implicit def madaTraversableUnstringize(from: String): Traversable[Char] = Unstringize(from)
    implicit def madaTraversableFromJObjectInput(from: java.io.ObjectInput): Traversable[AnyRef] = fromJObjectInput(from)
    implicit def madaTraversableFromJReader(from: java.io.Reader): Traversable[Char] = fromJReader(from)
}
