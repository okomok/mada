

// Copy_2 Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


case class Merge[A](_1: Traversable[A], _2: Traversable[A], _3: compare.Func[A]) extends Traversable[A] {
    override def start = new Traverser[A] {
        private val t1 = _1.start
        private val t2 = _2.start

        override def isEnd = !t1 && !t2
        override def deref = Sorted.derefBy(t1, t2, _3)
        override def increment = Sorted.incrementBy(t1, t2, _3)
    }
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
