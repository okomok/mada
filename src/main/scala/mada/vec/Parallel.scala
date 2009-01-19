

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object Parallel {
    def defaultGrainSize[A](v: Vector[A]): Long = 1
    def apply[A](v: Vector[A]): Parallel[A] = new Parallel(v, defaultGrainSize(v))
    def apply[A](v: Vector[A], grainSize: Long): Parallel[A] = new Parallel(v, grainSize)
}

class Parallel[A](v: Vector[A], grainSize: Long) {
    def foldLeft[B](z: B)(op: (B, A) => B): B = parallel.FoldLeft(v, z, op, grainSize)
    def foldRight[B](z: B)(op: (A, B) => B): B = parallel.FoldRight(v, z, op, grainSize)
    def foreach(f: A => Unit): Unit = parallel.Foreach(v, f, grainSize)
    def reduceLeft[B >: A](op: (B, A) => B): B = parallel.ReduceLeft(v, op, grainSize)
    def reduceRight[B >: A](op: (A, B) => B): B = parallel.ReduceRight(v, op, grainSize)
}
