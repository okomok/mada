

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


/**
 * Contains explicit convertions around <code>Iterator</code>.
 */
trait Conversions {

// compatibles

  // from
    def fromIterable[A](from: Iterable[A]): Iterator[A] = from.elements

    def fromJclEnumeration[A](from: java.util.Enumeration[A]): Iterator[A] = new Iterator[A] {
        override def hasNext = from.hasMoreElements
        override def next = from.nextElement
    }

    def fromJclIterator[A](from: java.util.Iterator[A]): Iterator[A] = new Iterator[A] {
        override def hasNext = from.hasNext
        override def next = from.next
    }

    def fromObjectInput(in: java.io.ObjectInput): Iterator[AnyRef] = new Iterator[AnyRef] {
        private var cur: Option[AnyRef] = readNext // Note null too is a valid data.

        override def hasNext = !cur.isEmpty
        override def next = {
            val tmp = cur.get
            cur = readNext
            tmp
        }

        private def readNext = try {
            Some(in.readObject)
        } catch {
            case _: java.io.EOFException => None
        }
    }

  // to
    def toJclEnumeration[A](from: Iterator[A]): java.util.Enumeration[A] = new java.util.Enumeration[A] {
        override def hasMoreElements = from.hasNext
        override def nextElement = from.next
    }

    def toJclIterator[A](from: Iterator[A]): java.util.Iterator[A] = new java.util.Iterator[A] {
        override def hasNext = from.hasNext
        override def next = from.next
        override def remove = throw new UnsupportedOperationException
    }

// incompatibles

  // to
    def stringize[A](it: Iterator[Char]): String = Stringize(it)
    def toHashMap[K, V](from: Iterator[(K, V)]): scala.collection.Map[K, V] = ToHashMap(from)
    def toHashSet[A](from: Iterator[A]): scala.collection.Set[A] = ToHashSet(from)
    def toIterable[A](from: => Iterator[A]): Iterable.Projection[A] = ToIterable(from)
}
