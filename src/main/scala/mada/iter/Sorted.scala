

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.iter


private[mada] object Sorted {
    def derefBy[A](pt1: Pointer[A], pt2: Pointer[A], lt: compare.Func[A]): A = {
        if (!pt1) {
            ~pt2
        } else if (!pt2) {
            ~pt1
        } else {
            if (lt(~pt2, ~pt1)) ~pt2 else ~pt1
        }
    }

    def incrementBy[A](pt1: Pointer[A], pt2: Pointer[A], lt: compare.Func[A]): Unit = {
        if (!pt1) {
            pt2.++
        } else if (!pt2) {
            pt1.++
        } else {
            if (lt(~pt2, ~pt1)) pt2.++ else pt1.++
        }
    }
}


// merge

private[mada] object Merge {
    def apply[A](it1: Iterable[A], it2: Iterable[A], lt: compare.Func[A]): Iterable[A] = Iterables.byName(iimpl(it1.elements, it2.elements, lt))
    def iimpl[A](it1: Iterator[A], it2: Iterator[A], lt: compare.Func[A]): Iterator[A] = new MergePointer(it1, it2, lt)
}

private[mada] class MergePointer[A](pt1: Pointer[A], pt2: Pointer[A], lt: compare.Func[A]) extends Pointer[A] {
    override def isEnd = !pt1 && !pt2
    override def deref = Sorted.derefBy(pt1, pt2, lt)
    override def increment = Sorted.incrementBy(pt1, pt2, lt)
}


// union

private[mada] object Union {
    def apply[A](it1: Iterable[A], it2: Iterable[A], lt: compare.Func[A]): Iterable[A] = Iterables.byName(iimpl(it1.elements, it2.elements, lt))
    def iimpl[A](it1: Iterator[A], it2: Iterator[A], lt: compare.Func[A]): Iterator[A] = new UnionPointer(it1, it2, lt)
}

private[mada] class UnionPointer[A](pt1: Pointer[A], pt2: Pointer[A], lt: compare.Func[A]) extends Pointer[A] {
    override def isEnd = !pt1 && !pt2
    override def deref = Sorted.derefBy(pt1, pt2, lt)
    override def increment = {
        if (!pt1) {
            pt2.++
        } else if (!pt2) {
            pt1.++
        } else {
            if (lt(~pt2, ~pt1)) {
                pt2.++
            } else if (lt(~pt1, ~pt2)) {
                pt1.++
            } else {
                pt1.++
                pt2.++
            }
        }
    }
}


// intersection

private[mada] object Intersection {
    def apply[A](it1: Iterable[A], it2: Iterable[A], lt: compare.Func[A]): Iterable[A] = Iterables.byName(iimpl(it1.elements, it2.elements, lt))
    def iimpl[A](it1: Iterator[A], it2: Iterator[A], lt: compare.Func[A]): Iterator[A] = new IntersectionPointer(it1, it2, lt)
}

private[mada] class IntersectionPointer[A](pt1: Pointer[A], pt2: Pointer[A], lt: compare.Func[A]) extends Pointer[A] {
    ready

    override def isEnd = !pt1 || !pt2
    override def deref = ~pt1
    override def increment = { pt1.++; pt2.++; ready }

    private def ready: Unit = {
        while (!pt1.isEnd && !pt2.isEnd)  {
            if (lt(~pt2, ~pt1)) {
                pt2.++
            } else if (lt(~pt1, ~pt2)) {
                pt1.++
            } else {
                return
            }
        }
    }
}


// difference

private[mada] object Difference {
    def apply[A](it1: Iterable[A], it2: Iterable[A], lt: compare.Func[A]): Iterable[A] = Iterables.byName(iimpl(it1.elements, it2.elements, lt))
    def iimpl[A](it1: Iterator[A], it2: Iterator[A], lt: compare.Func[A]): Iterator[A] = new DifferencePointer(it1, it2, lt)
}

private[mada] class DifferencePointer[A](pt1: Pointer[A], pt2: Pointer[A], lt: compare.Func[A]) extends Pointer[A] {
    ready

    override def isEnd = !pt1
    override def deref = ~pt1
    override def increment = { pt1.++; ready }

    private def ready: Unit = {
        while (!pt1.isEnd && !pt2.isEnd)  {
            if (lt(~pt2, ~pt1)) {
                pt2.++
            } else if (lt(~pt1, ~pt2)) {
                return
            } else {
                pt1.++
                pt2.++
            }
        }
    }
}


// symmetricDifference

private[mada] object SymmetricDifference {
    def apply[A](it1: Iterable[A], it2: Iterable[A], lt: compare.Func[A]): Iterable[A] = Iterables.byName(iimpl(it1.elements, it2.elements, lt))
    def iimpl[A](it1: Iterator[A], it2: Iterator[A], lt: compare.Func[A]): Iterator[A] = new SymmetricDifferencePointer(it1, it2, lt)
}

private[mada] class SymmetricDifferencePointer[A](pt1: Pointer[A], pt2: Pointer[A], lt: compare.Func[A]) extends Pointer[A] {
    ready

    override def isEnd = !pt1 && !pt2
    override def deref = Sorted.derefBy(pt1, pt2, lt)
    override def increment = { Sorted.incrementBy(pt1, pt2, lt); ready }

    private def ready: Unit = {
        while (!pt1.isEnd && !pt2.isEnd)  {
            if (lt(~pt2, ~pt1)) {
                return
            } else if (lt(~pt1, ~pt2)) {
                return
            } else {
                pt1.++
                pt2.++
            }
        }
    }
}
