

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


case class Parallel[A](_1: Vector[A], _2: Int) extends Forwarder[A] {
    util.assert(!isParallel(_1))
    precondition.positive(_2, "grain size")

    override protected val delegate = _1
    override def grainSize = _2

    override def parallel(_grainSize: Int): Vector[A] = { // parallel-parallel fusion
      if (_grainSize == grainSize) this else delegate.parallel(_grainSize)
    }

// value semantics
    override def equalsIf[B](that: Vector[B])(p: (A, B) => Boolean): Boolean = parallels.EqualsIf(delegate, that, p, grainSize)

// filter
    override def filter(p: A => Boolean): Vector[A] = parallels.Filter(delegate, p, grainSize)

    override def mutatingFilter(p: A => Boolean): Vector[A] = {
        flatten(
            delegate.parallelRegions(grainSize).map{ w => w.mutatingFilter(p).toIterative }
        )
    }

// map
    override def map[B](f: A => B): Vector[B] = parallels.Map(delegate, f, grainSize)
    override def flatMap[B](f: A => Vector[B]): Vector[B] = FlatMap(this, f)

// loop
    override def each(f: A => Unit): Unit = {
        import function.future

        if (grainSize == 1) {
            delegate.map{ e => future(f(e)) }.force.foreach{ u => u() }
        } else {
            delegate.parallelRegions(grainSize).map{ w => future(w.foreach(f)) }.
                force. // start tasks.
                    foreach{ u => u() } // join all.
        }
    }

// search
    override def seek(p: A => Boolean) = parallels.Seek(delegate, p, grainSize)

    override def count(p: A => Boolean): Int = {
        delegate.parallelRegions(grainSize).map{ w => w.count(p) }.
            reduce(_ + _)
    }

// sort
    override def sortBy(lt: compare.Func[A]): Vector[A] = parallels.Sort(delegate, lt, grainSize)

// copy
    override def copy: Vector[A] = Copy(this)

    override def copyTo[B >: A](that: Vector[B]): Vector[B] = {
        precondition.range(delegate.size, that.size, "parallel.copyTo")

        (delegate.divide(grainSize) zip that.divide(grainSize)).
            parallel(1).each{ case (v1, w1) => v1.copyTo(w1) }

        that.window(0, delegate.size)
    }

// associative folding
    override def fold(z: A)(op: (A, A) => A): A = {
        (single(z) ++ delegate).parallel(grainSize).reduce(op)
    }

    override def folder(z: A)(op: (A, A) => A) = parallels.Folder(delegate, z, op, grainSize)

    override def reduce(op: (A, A) => A): A = {
        precondition.notEmpty(delegate, "paralell.reduce")
        delegate.parallelRegions(grainSize).map{ w => w.reduce(op) }.
            reduce(op)
    }

    override def reducer(op: (A, A) => A): Vector[A] = parallels.Reducer(delegate, op, grainSize)
}
