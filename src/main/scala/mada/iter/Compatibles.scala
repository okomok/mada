

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


/**
 * Contains implicit conversions around <code>Iterable</code>.
 */
@provider
trait Compatibles { this: Iterables.type =>
    @returnThis val Compatibles: Compatibles = this
// from
    implicit def madaIterableFromJclIterable[A](from: java.lang.Iterable[A]): Iterable[A] = fromJclIterable(from)
    implicit def madaIterableFromJioObjectInput(from: java.io.ObjectInput): Iterable[AnyRef] = fromJioObjectInput(from)
    implicit def madaIterableFromJioReader(from: java.io.Reader): Iterable[Char] = fromJioReader(from)
// to
    implicit def madaIterableToJclIterable[A](from: Iterable[A]): java.lang.Iterable[A] = toJclIterable(from)
}
