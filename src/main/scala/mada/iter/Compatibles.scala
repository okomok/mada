

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


/**
 * Provides implicit convertions around <code>Iterator</code>.
 */
trait Compatibles {
    /**
     * Converts to <code>java.util.Enumeration</code>.
     */
    implicit def madaIteratorToJclEnumeration[A](from: Iterator[A]): java.util.Enumeration[A] = new java.util.Enumeration[A] {
        override def hasMoreElements = from.hasNext
        override def nextElement = from.next
    }

    /**
     * Converts to <code>java.util.Iterator</code>.
     */
    implicit def madaIteratorToJclIterator[A](from: Iterator[A]): java.util.Iterator[A] = new java.util.Iterator[A] {
        override def hasNext = from.hasNext
        override def next = from.next
        override def remove = throw new UnsupportedOperationException
    }

    /**
     * Converts from <code>Iterable</code>.
     */
    implicit def madaIteratorFromIterable[A](from: Iterable[A]): Iterator[A] = from.elements

    /**
     * Converts from <code>java.util.Enumeration</code>.
     */
    implicit def madaIteratorFromJclEnumeration[A](from: java.util.Enumeration[A]): Iterator[A] = new Iterator[A] {
        override def hasNext = from.hasMoreElements
        override def next = from.nextElement
    }

    /**
     * Converts from <code>java.util.Iterator</code>.
     */
    implicit def madaIteratorFromJclIterator[A](from: java.util.Iterator[A]): Iterator[A] = new Iterator[A] {
        override def hasNext = from.hasNext
        override def next = from.next
    }

    /**
     * Converts from <code>java.io.ObjectInput</code>.
     */
    implicit def madaIteratorFromObjectInput(in: java.io.ObjectInput): Iterator[AnyRef] = new Iterator[AnyRef] {
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
}
