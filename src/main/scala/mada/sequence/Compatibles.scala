

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence


@compatibles
trait Compatibles { self: Sequence.type =>
    implicit def madaSequenceFromSIterable[A](from: Iterable[A]): Sequence[A] = fromSIterable(from)
    implicit def madaSequenceToSIterable[A](from: Sequence[A]): Iterable[A] = from.toSIterable
    implicit def madaSequenceFromJIterable[A](from: java.lang.Iterable[A]): Sequence[A] = fromJIterable(from)
    implicit def madaSequenceToJIterable[A](from: Sequence[A]): java.lang.Iterable[A] = from.toJIterable
    implicit def madaSequenceUnstringize(from: String): Sequence[Char] = Unstringize(from)
    implicit def madaSequenceFromJObjectInput(from: java.io.ObjectInput): Sequence[AnyRef] = fromJObjectInput(from)
    implicit def madaSequenceFromJReader(from: java.io.Reader): Sequence[Char] = fromJReader(from)
}
