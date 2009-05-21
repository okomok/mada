

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence

/*
case class FromSIterable[+A](_1: Iterable[A]) extends Forwarder[A] {
    override protected val delegate: Sequence[A] = _1 match {
        case ToSIterable(from) => from // from-to fusion
        case _ => new _FromSIterable(_1)
    }

    override def toSIterable = _1 // to-from fusion
}*/

case class FromSIterable[+A](_1: Iterable[A]) extends Sequence[A] {
    override def begin = iterator.fromSIterator(_1.elements)
}

case class ToSSequence[+A](_1: Sequence[A]) extends scala.collection.Sequence[A] {
    override def elements = _1.begin.toSIterator
    override def apply(n: Int) = _1.at(n)
    override def length = _1.size
}


trait SIteratorForwarder[A] extends scala.Iterator[A] with util.Forwarder {
    override protected def delegate: scala.Iterator[A]

    override def hasNext = delegate.hasNext
    override def next = delegate.next
    override def take(n: Int): scala.Iterator[A] = delegate.take(n)
    override def drop(n: Int): scala.Iterator[A] = delegate.drop(n)
    override def slice(from: Int, until: Int): scala.Iterator[A] = delegate.slice(from, until)
    override def map[B](f: A => B): scala.Iterator[B] = delegate.map(f)
    override def ++[B >: A](that: => scala.Iterator[B]) = delegate.++(that)
    override def flatMap[B](f: A => scala.Iterator[B]): scala.Iterator[B] = delegate.flatMap(f)
    override def filter(p: A => Boolean): scala.Iterator[A] = delegate.filter(p)
    override def takeWhile(p: A => Boolean): scala.Iterator[A] = delegate.takeWhile(p)
    override def dropWhile(p: A => Boolean): scala.Iterator[A] = delegate.dropWhile(p)
    override def zip[B](that: scala.Iterator[B]) = delegate.zip(that)
    override def zipWithIndex = delegate.zipWithIndex
    override def foreach[U](f: A => U) = delegate.foreach(f)
    override def forall(p: A => Boolean): Boolean = delegate.forall(p)
    override def exists(p: A => Boolean): Boolean = delegate.exists(p)
    override def contains(elem: Any): Boolean = delegate.contains(elem)
    override def find(p: A => Boolean): Option[A] = delegate.find(p)
    //override def findIndexOf(p: A => Boolean): Int = delegate.findIndexOf(p)
    override def indexOf[B >: A](elem: B): Int = delegate.indexOf(elem)
    override def foldLeft[B](z: B)(op: (B, A) => B): B = delegate.foldLeft(z)(op)
    override def foldRight[B](z: B)(op: (A, B) => B): B = delegate.foldRight(z)(op)
    override def /:[B](z: B)(op: (B, A) => B): B = delegate./:(z)(op)
    override def :\[B](z: B)(op: (A, B) => B): B = delegate.:\(z)(op)
    override def reduceLeft[B >: A](op: (B, A) => B): B = delegate.reduceLeft(op)
    override def reduceRight[B >: A](op: (A, B) => B): B = delegate.reduceRight(op)
    override def buffered: scala.BufferedIterator[A] = delegate.buffered
    //override def counted = delegate.counted
    override def duplicate: (scala.Iterator[A], scala.Iterator[A]) = delegate.duplicate
    override def copyToArray[B >: A](xs: Array[B], start: Int) = delegate.copyToArray(xs, start)
    //override def readInto[B >: A](xs: Array[B], start: Int, sz: Int) = delegate.readInto(xs, start, sz)
    //override def readInto[B >: A](xs: Array[B], start: Int) = delegate.readInto(xs, start)
    //override def readInto[B >: A](xs: Array[B]) = delegate.readInto(xs)
    override def copyToBuffer[B >: A](dest: scala.collection.mutable.Buffer[B]) = delegate.copyToBuffer(dest)
    override def toList: scala.List[A] = delegate.toList
    //override def collect: Seq[A] = delegate.collect
    override def mkString(start: String, sep: String, end: String): String = delegate.mkString(start, sep, end)
    override def mkString(sep: String): String = delegate.mkString(sep)
    override def mkString: String = delegate.mkString
    override def addString(buf: StringBuilder, start: String, sep: String, end: String): StringBuilder = delegate.addString(buf, start, sep, end)
}
