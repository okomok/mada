

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


/**
 * Contains explicit conversions around <code>Iterable</code>.
 */
trait Conversions {
// compatibles
  // from
    def fromJclIterable[A](from: java.lang.Iterable[A]): Iterable[A] = jcl.IterableToIterable(from)
    def fromJioObjectInput(from: java.io.ObjectInput): Iterable[AnyRef] = jio.ObjectInputToIterable(from)
    def fromJioReader(from: java.io.Reader): Iterable[Char] = jio.ReaderToIterable(from)
  // to
    def toJclIterable[A](from: Iterable[A]): java.lang.Iterable[A] = jcl.IterableFromIterable(from)

// incompatibles
  // to
    def stringize[A](it: Iterable[Char]): String = Stringize(it)
    def stringFrom[A](it: Iterable[A]): String = StringFrom(it)
    def toHashMap[K, V](from: Iterable[(K, V)]): scala.collection.Map[K, V] = ToHashMap(from)
    def toHashSet[A](from: Iterable[A]): scala.collection.Set[A] = ToHashSet(from)
}
