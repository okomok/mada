

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


private
case class Parallel[A](_1: Vector[A], _2: Int) extends Forwarder[A] {
    assert(!IsParallel(_1))
    Precondition.positive(_2, "grain size")

    override protected val delegate = _1

// parallel support
    override def parallelBy(g: Int): Vector[A] = if (g == grainSize) this else delegate.parallelBy(g) // parallel.parallel fusion
    override def grainSize: Int = _2
// value semantics
    override def equalsIf[B](that: Vector[B])(p: (A, B) => Boolean): Boolean = ParallelEqualsIf(delegate, that, p, grainSize)
// map
    override def map[B](f: A => B): Vector[B] = ParallelMap(delegate, f, grainSize)
// filter
    override def mutatingFilter(p: A => Boolean): Vector[A] = ParallelMutatingFilter(delegate, p, grainSize)
// loop
    override def each(f: A => Unit): Unit = ParallelEach(delegate, f, grainSize)
// search
    override def seek(p: A => Boolean): Option[A] = ParallelSeek(delegate, p, grainSize)
    override def count(p: A => Boolean): Int = ParallelCount(delegate, p, grainSize)
    override def forall(p: A => Boolean): Boolean = ParallelSeek(delegate, function.not(p), grainSize).isEmpty
    override def exists(p: A => Boolean): Boolean = !ParallelSeek(delegate, p, grainSize).isEmpty
    override def find(p: A => Boolean): Option[A] = ParallelFind(delegate, p, grainSize)
// sort
    override def sort[B >: A](implicit c: Ordering[B]): Vector[A] = { ParallelSort(delegate, c, grainSize); this }
// copy
    override def copy: Vector[A] = ParallelCopy(delegate, grainSize)
    override def copyTo[B >: A](that: Vector[B]): Vector[B] = ParallelCopyTo(delegate, that, grainSize)
// associative folding
    override def fold[B >: A](z: B)(op: (B, B) => B): B = ParallelFold(delegate, z, op, grainSize)
    override def reduce[B >: A](op: (B, B) => B): B = ParallelReduce(delegate, op, grainSize)
    override def scan[B >: A](z: B)(op: (B, B) => B): Vector[B] = ParallelScan(delegate, z, op, grainSize)
    override def scan1[B >: A](op: (B, B) => B): Vector[B] = ParallelScan1(delegate, op, grainSize)
// conversion
    override def toArray[B >: A : ClassManifest]: Array[B] = {
        val r = new Array[B](size)
        ParallelCopyTo(delegate, from(r), grainSize)
        r
    }
}


private
object IsParallel {
    def apply[A](v: Vector[A]): Boolean = v.isInstanceOf[Parallel[_]]
}
