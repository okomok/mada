

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


trait IteratorProxy[A] extends Iterator[A] with Proxy {
    override def self: Iterator[A]

    override def hasNext = self.hasNext
    override def next = self.next
    override def take(n: Int): Iterator[A] = self.take(n)
    override def drop(n: Int): Iterator[A] = self.drop(n)
    override def slice(from: Int, until: Int): Iterator[A] = self.slice(from, until)
    override def map[B](f: A => B): Iterator[B] = self.map(f)
    override def ++[B >: A](that: => Iterator[B]) = self.++(that)
    override def flatMap[B](f: A => Iterator[B]): Iterator[B] = self.flatMap(f)
    override def filter(p: A => Boolean): Iterator[A] = self.filter(p)
    override def takeWhile(p: A => Boolean): Iterator[A] = self.takeWhile(p)
    override def dropWhile(p: A => Boolean): Iterator[A] = self.dropWhile(p)
    override def zip[B](that: Iterator[B]) = self.zip(that)
    override def zipWithIndex = self.zipWithIndex
    override def foreach(f: A => Unit) = self.foreach(f)
    override def forall(p: A => Boolean): Boolean = self.forall(p)
    override def exists(p: A => Boolean): Boolean = self.exists(p)
    override def contains(elem: Any): Boolean = self.contains(elem)
    override def find(p: A => Boolean): Option[A] = self.find(p)
    override def findIndexOf(p: A => Boolean): Int = self.findIndexOf(p)
    override def indexOf[B >: A](elem: B): Int = self.indexOf(elem)
    override def foldLeft[B](z: B)(op: (B, A) => B): B = self.foldLeft(z)(op)
    override def foldRight[B](z: B)(op: (A, B) => B): B = self.foldRight(z)(op)
    override def /:[B](z: B)(op: (B, A) => B): B = self./:(z)(op)
    override def :\[B](z: B)(op: (A, B) => B): B = self.:\(z)(op)
    override def reduceLeft[B >: A](op: (B, A) => B): B = self.reduceLeft(op)
    override def reduceRight[B >: A](op: (A, B) => B): B = self.reduceRight(op)
    override def buffered: BufferedIterator[A] = self.buffered
    override def counted = self.counted
    override def duplicate: (Iterator[A], Iterator[A]) = self.duplicate
    override def copyToArray[B >: A](xs: Array[B], start: Int) = self.copyToArray(xs, start)
    override def readInto[B >: A](xs: Array[B], start: Int, sz: Int) = self.readInto(xs, start, sz)
    override def readInto[B >: A](xs: Array[B], start: Int) = self.readInto(xs, start)
    override def readInto[B >: A](xs: Array[B]) = self.readInto(xs)
    override def copyToBuffer[B >: A](dest: scala.collection.mutable.Buffer[B]) = self.copyToBuffer(dest)
    override def toList: List[A] = self.toList
    override def collect: Seq[A] = self.collect
    override def mkString(start: String, sep: String, end: String): String = self.mkString(start, sep, end)
    override def mkString(sep: String): String = self.mkString(sep)
    override def mkString: String = self.mkString
    override def addString(buf: StringBuilder, start: String, sep: String, end: String): StringBuilder = self.addString(buf, start, sep, end)
}


object Iterator2JclIterator {
    def apply[A](it: Iterator[A]): java.util.Iterator[A] = new java.util.Iterator[A] {
        override def hasNext = it.hasNext
        override def next = it.next
        override def remove = throw new UnsupportedOperationException
    }
}

object JclIterator2Iterator {
    def apply[A](it: java.util.Iterator[A]): Iterator[A] = new Iterator[A] {
        override def hasNext = it.hasNext
        override def next = it.next
    }
}

object IteratorCompatibles {
    implicit def madaIterator2JclIterator[A](from: Iterator[A]): java.util.Iterator[A] = Iterator2JclIterator(from)
    implicit def madaJclIterator2Iterator[A](from: java.util.Iterator[A]): Iterator[A] = JclIterator2Iterator(from)
}
