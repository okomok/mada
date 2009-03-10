

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


/**
 * Contains explicit conversions around <code>Iterator</code>.
 */
trait Conversions {


// compatibles

  // from
    def fromJclEnumeration[A](from: java.util.Enumeration[A]): Iterator[A] = new Iterator[A] {
        override def hasNext = from.hasMoreElements
        override def next = from.nextElement
    }

    def fromJclIterable[A](from: java.lang.Iterable[A]): Iterable[A] = jcl.IterableToIterable(from)
    def fromObjectInput(from: java.io.ObjectInput): Iterable[AnyRef] = jcl.ObjectInputToIterable(from)

  // to
    def toJclEnumeration[A](from: Iterator[A]): java.util.Enumeration[A] = new java.util.Enumeration[A] {
        override def hasMoreElements = from.hasNext
        override def nextElement = from.next
    }

    def toJclIterable[A](from: Iterable[A]): java.lang.Iterable[A] = jcl.IterableFromIterable(from)


// incompatibles

  // to
    def stringize[A](it: Iterable[Char]): String = Stringize(it)
    def toHashMap[K, V](from: Iterable[(K, V)]): scala.collection.Map[K, V] = ToHashMap(from)
    def toHashSet[A](from: Iterable[A]): scala.collection.Set[A] = ToHashSet(from)
}
