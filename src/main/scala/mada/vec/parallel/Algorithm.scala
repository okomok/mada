

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec.parallel


private[mada] trait Algorithm[A] extends Adapter.Transform[A] {
    // value semantics
    override def equalsWith[B](that: Vector[B])(p: Functions.Predicate2[A, B]): Boolean
    override def hashCode: Int = underlying.hashCode
    // regions
    override def region(_start: Int, _end: Int): Vector[A] = affectParallel(underlying.region(_start, _end))
    // division
    override def divide(n: Int): Vector[Vector[A]] = underlying.divide(n)
    override def splitAt(i: Int): (Vector[A], Vector[A]) = underlying.splitAt(i)
    override def span(p: A => Boolean): (Vector[A], Vector[A]) = underlying.span(p)
    override def break(p: A => Boolean): (Vector[A], Vector[A]) = underlying.break(p)
    // filter
    override def filter(p: A => Boolean): Vector[A] = underlying.filter(p)
    override def mutatingFilter(p: A => Boolean): Vector[A] = underlying.mutatingFilter(p)
    // map
    override def map[B](f: A => B): Vector[B]
    override def flatMap[B](f: A => Vector[B]): Vector[B] = affectParallel(underlying.flatMap(f))
    override def asVectorOf[B]: Vector[B] = affectParallel(underlying.asVectorOf[B])
    // foreach
    override def loop[F <: (A => Boolean)](i: Int, j: Int, f: F): F = underlying.loop(i, j, f)
    override def pareach(f: A => Unit): Unit
    // search
    override def seek(p: A => Boolean): Option[A]
    override def count(p: A => Boolean): Int
    // folding
    override def folderLeft[B](z: B)(op: (B, A) => B): Vector[B] = underlying.folderLeft(z)(op)
    override def folderRight[B](z: B)(op: (A, B) => B): Vector[B] = underlying.folderRight(z)(op)
    override def reducerLeft[B >: A](op: (B, A) => B): Vector[B] = underlying.reducerLeft(op)
    override def reducerRight[B >: A](op: (A, B) => B): Vector[B] = underlying.reducerRight(op)
    // sort
    override def sortWith(lt: Compare.Type[A]): Vector[A] = underlying.sortWith(lt)
    // concatenation
    override def append(that: Vector[A]): Vector[A] = {
        val x = underlying.append(that.unparallel)
        if (that.isParallel) {
            x.parallel(Math.min(grainSize, that.grainSize))
        } else {
            x
        }
    }
    // permutation
    override def permutation(f: Int => Int): Vector[A] = affectParallel(underlying.permutation(f))
    override def cycle(n: Int): Vector[A] = affectParallel(underlying.cycle(n))
    override def nth: Vector[A] = affectParallel(underlying.nth)
    override def reverse: Vector[A] = affectParallel(underlying.reverse)
    override def step(n: Int): Vector[A] = affectParallel(underlying.step(n))
    override def rotate(i: Int): Vector[A] = affectParallel(underlying.rotate(i))
    // zip
    override def zip[B](that: Vector[B]): Vector[(A, B)] = {
        val x = underlying.zip(that.unparallel)
        if (that.isParallel) {
            x.parallel(Math.min(grainSize, that.grainSize))
        } else {
            x
        }
    }
    // attributes
    override def force: Vector[A] = affectParallel(underlying.force)
    override def lazyValues : Vector[A] = affectParallel(underlying.lazyValues)
    override def bounds: Vector[A] = affectParallel(underlying.bounds)
    override def readOnly: Vector[A] = affectParallel(underlying.readOnly)
    override def identity: Vector[A] = affectParallel(underlying.identity)
    // copy
    override def copyTo[B >: A](that: Vector[B]): Vector[A]
    // parallel support
    override def unparallel: Vector[A] = underlying.unparallel
    override def grainSize: Int
    override def defaultGrainSize: Int = underlying.defaultGrainSize
    override def isParallel: Boolean = true
    override def join: Unit = underlying.join
    // parallel folding
    override def fold(z: A)(op: (A, A) => A): A
    override def folder(z: A)(op: (A, A) => A): Vector[A]
    override def reduce(op: (A, A) => A): A
    override def reducer(op: (A, A) => A): Vector[A]
    // implementation helpers
    protected def affectParallel[B](that: Vector[B]): Vector[B] = that.parallel(grainSize)
}
