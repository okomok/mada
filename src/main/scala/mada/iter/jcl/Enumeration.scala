

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter.jcl


private[mada] object EnumerationFromIterator {
    def apply[A](from: Iterator[A]): java.util.Enumeration[A] = new java.util.Enumeration[A] {
        override def hasMoreElements = from.hasNext
        override def nextElement = from.next
    }
}

private[mada] object EnumerationToIterator {
    def apply[A](from: java.util.Enumeration[A]): Iterator[A] = new Iterator[A] {
        override def hasNext = from.hasMoreElements
        override def next = from.nextElement
    }
}
