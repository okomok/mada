

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


trait Forwarder[+A] extends Traversable[A] with any.Forwarder {
    override protected def delegate: Traversable[A]

    override def begin: Traverser[A] = delegate.begin

    override def equals(that: Any): Boolean = delegate.equals(that)
    override def equalsIf[B](that: Traversable[B])(p: (A, B) => Boolean): Boolean = delegate.equalsIf(that)(p)
    override def hashCode: Int = delegate.hashCode

    override def isEmpty: Boolean = delegate.isEmpty
    override def length: Int = delegate.length
    override def append[B >: A](that: Traversable[B]): Traversable[B] = delegate.append(that)
    override def map[B](f: A => B): Traversable[B] = delegate.map(f)
    override def flatMap[B](f: A => Traversable[B]): Traversable[B] = delegate.flatMap(f)
    override def filter(p: A => Boolean): Traversable[A] = delegate.filter(p)
    override def partition(p: A => Boolean): (Traversable[A], Traversable[A]) = delegate.partition(p)
    override def groupBy[K](f: A => K): scala.collection.Map[K, Traversable[A]] = delegate.groupBy(f)
    override def foreach[B](f: A => B): Unit = delegate.foreach(f)
    override def forall(p: A => Boolean): Boolean = delegate.forall(p)
    override def exists(p: A => Boolean): Boolean = delegate.exists(p)
    override def count(p: A => Boolean): Int = delegate.count(p)
    override def find(p: A => Boolean): Option[A] = delegate.find(p)
    override def foldLeft[B](z: B)(op: (B, A) => B): B = delegate.foldLeft(z)(op)
    //override def /:[B](z: B)(op: (B, A) => B): B = delegate./:(z)(op)
    override def reduceLeft[B >: A](op: (B, A) => B): B = delegate.reduceLeft(op)
    override def folderLeft[B](z: B)(op: (B, A) => B): Traversable[B] = delegate.folderLeft(z)(op)
    override def reducerLeft[B >: A](op: (B, A) => B): Traversable[B] = delegate.reducerLeft(op)
    override def head: A = delegate.head
    override def headOption: Option[A] = delegate.headOption
    override def tail: Traversable[A] = delegate.tail
    override def last: A = delegate.last
    override def lastOption: Option[A] = delegate.lastOption
    override def take(n: Int): Traversable[A] = delegate.take(n)
    override def drop(n: Int): Traversable[A] = delegate.drop(n)
    override def slice(from: Int, until: Int): Traversable[A] = delegate.slice(from, until)
    override def takeWhile(p: A => Boolean): Traversable[A] = delegate.takeWhile(p)
    override def dropWhile(p: A => Boolean): Traversable[A] = delegate.dropWhile(p)
    override def span(p: A => Boolean): (Traversable[A], Traversable[A]) = delegate.span(p)
    override def splitAt(n: Int): (Traversable[A], Traversable[A]) = delegate.splitAt(n)
    //override def copyToBuffer[B >: A](dest: Buffer[B]) = delegate.copyToBuffer(dest)
    //override def copyToArray[B >: A](xs: Array[B], begin: Int, len: Int) = delegate.copyToArray(xs, begin, len)
    //override def copyToArray[B >: A](xs: Array[B], begin: Int) = delegate.copyToArray(xs, begin)
    //override def toArray[B >: A]: Array[B] = delegate.toArray
    override def toIterable: Iterable[A] = delegate.toIterable

    override def at(n: Int): A = delegate.at(n)
    override def contains(e: Any): Boolean = delegate.contains(e)
    override def cycle: Traversable[A] = delegate.cycle
    override def times(n: Int): Traversable[A] = delegate.times(n)
    override def _flatten[B](_this: Traversable[Traversable[B]]): Traversable[B] = delegate._flatten(_this)
    override def step(n: Int): Traversable[A] = delegate.step(n)
    override def unique: Traversable[A] = delegate.unique
    override def uniqueBy(p: (A, A) => Boolean): Traversable[A] = delegate.uniqueBy(p)
    override def _stringize(_this: Traversable[Char]): String = delegate._stringize(_this)
    override def _toHashMap[K, V](_this: Traversable[(K, V)]): scala.collection.Map[K, V] = delegate._toHashMap(_this)
    override def _toHashSet[B](_this: Traversable[B]): scala.collection.Set[B] = delegate._toHashSet(_this)
    override def _toVector[B](_this: Traversable[B]): Vector[B] = delegate._toVector(_this)
    override def zip[B](that: Traversable[B]): Traversable[(A, B)] = delegate.zip(that)

    override def merge[B >: A](that: Traversable[B])(implicit c: Compare[B]): Traversable[B] = delegate.merge(that)(c)
    override def mergeBy[B >: A](that: Traversable[B])(lt: compare.Func[B]): Traversable[B] = delegate.mergeBy(that)(lt)
    override def union[B >: A](that: Traversable[B])(implicit c: Compare[B]): Traversable[B] = delegate.union(that)(c)
    override def unionBy[B >: A](that: Traversable[B])(lt: compare.Func[B]): Traversable[B] = delegate.unionBy(that)(lt)
    override def intersection[B >: A](that: Traversable[B])(implicit c: Compare[B]): Traversable[B] = delegate.intersection(that)(c)
    override def intersectionBy[B >: A](that: Traversable[B])(lt: compare.Func[B]): Traversable[B] = delegate.intersectionBy(that)(lt)
    override def difference[B >: A](that: Traversable[B])(implicit c: Compare[B]): Traversable[B] = delegate.difference(that)(c)
    override def differenceBy[B >: A](that: Traversable[B])(lt: compare.Func[B]): Traversable[B] = delegate.differenceBy(that)(lt)
    override def symmetricDifference[B >: A](that: Traversable[B])(implicit c: Compare[B]): Traversable[B] = delegate.symmetricDifference(that)(c)
    override def symmetricDifferenceBy[B >: A](that: Traversable[B])(lt: compare.Func[B]): Traversable[B] = delegate.symmetricDifferenceBy(that)(lt)
}
