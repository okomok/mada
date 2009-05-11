

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


private[mada] class MergeTraverser[A](t1: Traverser[A], t2: Traverser[A], lt: compare.Func[A]) extends Traverser[A] {
    override def isEnd = !t1 && !t2
    override def deref = Sorted.derefBy(t1, t2, lt)
    override def increment = Sorted.incrementBy(t1, t2, lt)
}


private object Sorted {

    def derefBy[A](t1: Traverser[A], t2: Traverser[A], lt: compare.Func[A]): A = {
        if (!t1) {
            ~t2
        } else if (!t2) {
            ~t1
        } else {
            if (lt(~t2, ~t1)) ~t2 else ~t1
        }
    }

    def incrementBy[A](t1: Traverser[A], t2: Traverser[A], lt: compare.Func[A]): Unit = {
        if (!t1) {
            t2.++
        } else if (!t2) {
            t1.++
        } else {
            if (lt(~t2, ~t1)) t2.++ else t1.++
        }
    }

}
