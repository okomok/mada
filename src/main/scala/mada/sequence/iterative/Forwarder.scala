

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.iterative


trait Forwarder[+A] extends Iterative[A] with SequenceForwarder[A] {
    override protected def delegate: Iterative[A]

    protected def around[B](that: => Iterative[B]): Iterative[B] = that
    protected def around2[B, C](that: => (Iterative[B], Iterative[C])): (Iterative[B], Iterative[C]) = {
        val _that = that
        (around(_that._1), around(_that._2))
    }

    override def begin: Iterator[A] = delegate.begin

    override def equalsIf[B](that: Iterative[B])(p: (A, B) => Boolean): Boolean = delegate.equalsIf(that)(p)
    override def isEmpty: Boolean = delegate.isEmpty
    override def size: Int = delegate.size
    override def append[B >: A](that: Iterative[B]): Iterative[B] = around(delegate.append(that))
    override def map[B](f: A => B): Iterative[B] = around(delegate.map(f))
    override def flatMap[B](f: A => Iterative[B]): Iterative[B] = around(delegate.flatMap(f))
    override def filter(p: A => Boolean): Iterative[A] = around(delegate.filter(p))
    override def remove(p: A => Boolean): Iterative[A] = around(delegate.remove(p))
    override def partition(p: A => Boolean): (Iterative[A], Iterative[A]) = around2(delegate.partition(p))
    override def groupBy[K](f: A => K): scala.collection.Map[K, Iterative[A]] = delegate.groupBy(f)
    override def foreach(f: A => Unit): Unit = delegate.foreach(f)
    override def forall(p: A => Boolean): Boolean = delegate.forall(p)
    override def exists(p: A => Boolean): Boolean = delegate.exists(p)
    override def count(p: A => Boolean): Int = delegate.count(p)
    override def find(p: A => Boolean): Option[A] = delegate.find(p)
    override def foldLeft[B](z: B)(op: (B, A) => B): B = delegate.foldLeft(z)(op)
    override def reduceLeft[B >: A](op: (B, A) => B): B = delegate.reduceLeft(op)
    override def folderLeft[B](z: B)(op: (B, A) => B): Iterative[B] = around(delegate.folderLeft(z)(op))
    override def reducerLeft[B >: A](op: (B, A) => B): Iterative[B] = around(delegate.reducerLeft(op))
    override def head: A = delegate.head
    override def headOption: Option[A] = delegate.headOption
    override def tail: Iterative[A] = around(delegate.tail)
    override def last: A = delegate.last
    override def lastOption: Option[A] = delegate.lastOption
    override def take(n: Int): Iterative[A] = around(delegate.take(n))
    override def drop(n: Int): Iterative[A] = around(delegate.drop(n))
    override def slice(from: Int, until: Int): Iterative[A] = around(delegate.slice(from, until))
    override def takeWhile(p: A => Boolean): Iterative[A] = around(delegate.takeWhile(p))
    override def dropWhile(p: A => Boolean): Iterative[A] = around(delegate.dropWhile(p))
    override def span(p: A => Boolean): (Iterative[A], Iterative[A]) = around2(delegate.span(p))
    override def splitAt(n: Int): (Iterative[A], Iterative[A]) = around2(delegate.splitAt(n))

    override def at(n: Int): A = delegate.at(n)
    override def contains(e: Any): Boolean = delegate.contains(e)
    override def cycle: Iterative[A] = around(delegate.cycle)
    override def times(n: Int): Iterative[A] = delegate.times(n)
    override def force: Iterative[A] = around(delegate.force)
    override def _flatten[B](_this: Iterative[Iterative[B]]): Iterative[B] = around(delegate.asInstanceOf[Iterative[Iterative[B]]].flatten)
    override def memoize: Iterative[A] = around(delegate.memoize)
    override def mix(x: Mixin): Iterative[A] = around(delegate.mix(x))
    override def singlePass: Iterative[A] = around(delegate.singlePass)
    override def step(n: Int): Iterative[A] = delegate.step(n)
    override def unique: Iterative[A] = around(delegate.unique)
    override def uniqueBy(p: (A, A) => Boolean): Iterative[A] = around(delegate.uniqueBy(p))
    override def _unsplit[B](_this: Iterative[Iterative[B]], sep: Iterative[B]): Iterative[B] = around(delegate.asInstanceOf[Iterative[Iterative[B]]].unsplit(sep))
    override def zip[B](that: Iterative[B]): Iterative[(A, B)] = around(delegate.zip(that))
    override def _unzip[B, C](_this: Iterative[(B, C)]): (Iterative[B], Iterative[C]) = around2(delegate.asInstanceOf[Iterative[(B, C)]].unzip)
    override def zipBy[B, C](that: Iterative[B])(f: (A, B) => C): Iterative[C] = around(delegate.zipBy(that)(f))
    override def _stringize(_this: Iterative[Char]): String = delegate.asInstanceOf[Iterative[Char]].stringize
    override def toSome: ToSome[A] = delegate.toSome
    override def toList: List[A] = delegate.toList
    override def _toVector[B](_this: Iterative[B]): Vector[B] = delegate.asInstanceOf[Iterative[B]].toVector
    override def toSeq: Seq[A] = delegate.toSeq
    override def toSList: scala.collection.immutable.List[A] = delegate.toSList
    override def _toSHashMap[K, V](_this: Iterative[(K, V)]): scala.collection.Map[K, V] = delegate.asInstanceOf[Iterative[(K, V)]].toSHashMap
    override def _toSHashSet[B](_this: Iterative[B]): scala.collection.Set[B] = delegate.asInstanceOf[Iterative[B]].toSHashSet
    override def _toJIterable[B](_this: Iterative[B]): java.lang.Iterable[B] = delegate.asInstanceOf[Iterative[B]].toJIterable

    override def merge[B >: A](that: Iterative[B])(implicit c: Compare[B]): Iterative[B] = around(delegate.merge(that)(c))
    override def mergeBy[B >: A](that: Iterative[B])(lt: compare.Func[B]): Iterative[B] = around(delegate.mergeBy(that)(lt))
    override def union[B >: A](that: Iterative[B])(implicit c: Compare[B]): Iterative[B] = around(delegate.union(that)(c))
    override def unionBy[B >: A](that: Iterative[B])(lt: compare.Func[B]): Iterative[B] = around(delegate.unionBy(that)(lt))
    override def intersection[B >: A](that: Iterative[B])(implicit c: Compare[B]): Iterative[B] = around(delegate.intersection(that)(c))
    override def intersectionBy[B >: A](that: Iterative[B])(lt: compare.Func[B]): Iterative[B] = around(delegate.intersectionBy(that)(lt))
    override def difference[B >: A](that: Iterative[B])(implicit c: Compare[B]): Iterative[B] = around(delegate.difference(that)(c))
    override def differenceBy[B >: A](that: Iterative[B])(lt: compare.Func[B]): Iterative[B] = around(delegate.differenceBy(that)(lt))
    override def symmetricDifference[B >: A](that: Iterative[B])(implicit c: Compare[B]): Iterative[B] = around(delegate.symmetricDifference(that)(c))
    override def symmetricDifferenceBy[B >: A](that: Iterative[B])(lt: compare.Func[B]): Iterative[B] = around(delegate.symmetricDifferenceBy(that)(lt))
}
