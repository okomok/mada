

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Contains utility methods operating on <code>Iterator</code>.
 */
object Iterators {
    /**
     * @return  <code>equalWith(it, jt)(Functions.equal)
     */
     def equal[A, B](it: Iterator[A], jt: Iterator[B]): Boolean = {
         equalWith(it, jt)(Functions.equal)
     }

    /**
     * Returns <code>true</code> iif elements and length are the same.
     */
    def equalWith[A, B](it: Iterator[A], jt: Iterator[B])(p: Functions.Predicate2[A, B]): Boolean = {
        while (it.hasNext && jt.hasNext) {
            if (!p(it.next, jt.next)) {
                return false
            }
        }
        !it.hasNext && !jt.hasNext
    }

    /**
     * Returns the length.
     */
    def length[A, B](it: Iterator[A]): Int = {
        var l = 0
        while (it.hasNext) {
            l += 1
            it.next
        }
        l
    }

    /**
     * The unfolding
     */
    def unfoldRight[A, B](z: A)(op: A => Option[(B, A)]): Iterator[B] = new Iterator[B] {
        private var acc = op(z)
        override def hasNext = !acc.isEmpty
        override def next = {
            val tmp = acc.get
            acc = op(tmp._2)
            tmp._1
        }
    }

    /**
     * An infinite iterator of repeated applications of <code>op</code> to <code>z</code>.
     */
    def iterate[A](z: A)(op: A => A): Iterator[A] = new Iterator[A] {
        private var acc = z
        override def hasNext = true
        override def next = {
            val tmp = acc
            acc = op(acc)
            tmp
        }
    } // unfoldRight(z)({ x => Some(x, op(x)) }) always needs heap-allocation of Option.

    /**
     * An infinite iterator, with <code>e</code> the value of every element.
     */
    def repeat[A](e: A): Iterator[A] = iterate(e)(Functions.identity[A])

    /**
     * An infinite repetition of <code>it</code>.
     */
    def cycle[A](it: Iterator[A]): Iterator[A] = {
        val buf = new java.util.ArrayList[A]
        var firstTime = true
        val f = { (u: Unit) =>
            if (firstTime) {
                firstTime = false
                withSideEffect(it)({ e => buf.add(e) })
            } else {
                jclIteratorIterator(buf.iterator)
            }
        }
        repeat(()).flatMap(f)
    }

    /**
     * Iterates with side-effect <code>f</code>.
     */
    def withSideEffect[A](it: Iterator[A])(f: A => Any): Iterator[A] = new Iterator[A] {
        override def hasNext = it.hasNext
        override def next = {
            val e = it.next
            f(e)
            e
        }
    }

    /**
     * Provides infix operators using implicit conversions.
     */
    val Infix = new {
        class MadaIterators[A](_1: Iterator[A]) {
            def equal[B](_2: Iterator[B]) = Iterators.equal(_1, _2)
            def equalWith[B](_2: Iterator[B])(_3: Functions.Predicate2[A, B]) = Iterators.equalWith(_1, _2)(_3)
            def length = Iterators.length(_1)
            def cycle = Iterators.cycle(_1)
            def withSideEffect(_2: A => Any) = Iterators.withSideEffect(_1)(_2)
        }
        implicit def iterator2MadaIterators[A](_1: Iterator[A]): MadaIterators[A] = new MadaIterators(_1)
    }

    /**
     * Implements a proxy for iterator objects.
     */
    trait IteratorProxy[A] extends Iterator[A] with Proxies.ProxyOf[Iterator[A]] {
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

    /**
     * Provides implicit convertions around <code>Iterator</code>.
     */
    val Compatibles = new {
        implicit def madaIterator2JclIterator[A](from: Iterator[A]): java.util.Iterator[A] = jclIterator(from)
        implicit def madaJclIterator2Iterator[A](from: java.util.Iterator[A]): Iterator[A] = jclIteratorIterator(from)
        implicit def madaIterator2JclEnumeration[A](from: java.util.Iterator[A]): java.util.Enumeration[A] = jclEnumeration(from)
        implicit def madaJclEnumeration2Iterator[A](from: java.util.Enumeration[A]): Iterator[A] = jclEnumerationIterator(from)
    }

    /**
     * Converts to <code>java.util.Iterator</code>.
     */
    def jclIterator[A](it: Iterator[A]): java.util.Iterator[A] = new java.util.Iterator[A] {
        override def hasNext = it.hasNext
        override def next = it.next
        override def remove = throw new UnsupportedOperationException
    }

    /**
     * Converts from <code>java.util.Iterator</code>.
     */
    def jclIteratorIterator[A](it: java.util.Iterator[A]): Iterator[A] = new Iterator[A] {
        override def hasNext = it.hasNext
        override def next = it.next
    }

    /**
     * Converts to <code>java.util.Enumeration</code>.
     */
    def jclEnumeration[A](it: Iterator[A]): java.util.Enumeration[A] = new java.util.Enumeration[A] {
        override def hasMoreElements = it.hasNext
        override def nextElement = it.next
    }

    /**
     * Converts from <code>java.util.Enumeration</code>.
     */
    def jclEnumerationIterator[A](it: java.util.Enumeration[A]): Iterator[A] = new Iterator[A] {
        override def hasNext = it.hasMoreElements
        override def next = it.nextElement
    }
}
