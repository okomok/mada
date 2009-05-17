

// Copy_2 Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.traversable


private object Sorted {

    def derefBy[A](t1: Traverser[A], t2: Traverser[A], _3: compare.Func[A]): A = {
        if (!t1) {
            ~t2
        } else if (!t2) {
            ~t1
        } else {
            if (_3(~t2, ~t1)) ~t2 else ~t1
        }
    }

    def incrementBy[A](t1: Traverser[A], t2: Traverser[A], _3: compare.Func[A]): Unit = {
        if (!t1) {
            t2.++
        } else if (!t2) {
            t1.++
        } else {
            if (_3(~t2, ~t1)) t2.++ else t1.++
        }
    }

}


case class Merge[A](_1: Traversable[A], _2: Traversable[A], _3: Compare[A]) extends Forwarder[A] {
    override protected val delegate = _1.mergeBy(_2)(_3)
}

case class MergeBy[A](_1: Traversable[A], _2: Traversable[A], _3: compare.Func[A]) extends Traversable[A] {
    override def begin = new Traverser[A] {
        private val t1 = _1.begin
        private val t2 = _2.begin

        override def isEnd = !t1 && !t2
        override def deref = Sorted.derefBy(t1, t2, _3)
        override def increment = Sorted.incrementBy(t1, t2, _3)
    }
}


case class Union[A](_1: Traversable[A], _2: Traversable[A], _3: Compare[A]) extends Forwarder[A] {
    override protected val delegate = _1.unionBy(_2)(_3)
}

case class UnionBy[A](_1: Traversable[A], _2: Traversable[A], _3: compare.Func[A]) extends Traversable[A] {
    override def begin = new Traverser[A] {
        private val t1 = _1.begin
        private val t2 = _2.begin

        override def isEnd = !t1 && !t2
        override def deref = {
            preDeref
            Sorted.derefBy(t1, t2, _3)
        }
        override def increment = {
            preIncrement
            if (!t1) {
                t2.++
            } else if (!t2) {
                t1.++
            } else {
                if (_3(~t2, ~t1)) {
                    t2.++
                } else if (_3(~t1, ~t2)) {
                    t1.++
                } else {
                    t1.++
                    t2.++
                }
            }
        }
    }
}


case class Intersection[A](_1: Traversable[A], _2: Traversable[A], _3: Compare[A]) extends Forwarder[A] {
    override protected val delegate = _1.intersectionBy(_2)(_3)
}

case class IntersectionBy[A](_1: Traversable[A], _2: Traversable[A], _3: compare.Func[A]) extends Traversable[A] {
    override def begin = new Traverser[A] {
        private val t1 = _1.begin
        private val t2 = _2.begin
        ready

        override def isEnd = !t1 || !t2
        override def deref = ~t1
        override def increment = {
            t1.++
            t2.++
            ready
        }

        private def ready: Unit = {
            while (t1 && t2)  {
                if (_3(~t2, ~t1)) {
                    t2.++
                } else if (_3(~t1, ~t2)) {
                    t1.++
                } else {
                    return
                }
            }
        }
    }
}


case class Difference[A](_1: Traversable[A], _2: Traversable[A], _3: Compare[A]) extends Forwarder[A] {
    override protected val delegate = _1.differenceBy(_2)(_3)
}

case class DifferenceBy[A](_1: Traversable[A], _2: Traversable[A], _3: compare.Func[A]) extends Traversable[A] {
    override def begin = new Traverser[A] {
        private val t1 = _1.begin
        private val t2 = _2.begin
        ready

        override def isEnd = !t1
        override def deref = ~t1
        override def increment = {
            t1.++
            ready
        }

        private def ready: Unit = {
            while (t1 && t2)  {
                if (_3(~t2, ~t1)) {
                    t2.++
                } else if (_3(~t1, ~t2)) {
                    return
                } else {
                    t1.++
                    t2.++
                }
            }
        }
    }
}


case class SymmetricDifference[A](_1: Traversable[A], _2: Traversable[A], _3: Compare[A]) extends Forwarder[A] {
    override protected val delegate = _1.symmetricDifferenceBy(_2)(_3)
}

case class SymmetricDifferenceBy[A](_1: Traversable[A], _2: Traversable[A], _3: compare.Func[A]) extends Traversable[A] {
    override def begin = new Traverser[A] {
        private val t1 = _1.begin
        private val t2 = _2.begin
        ready

        override def isEnd = !t1 && !t2
        override def deref = {
            preDeref
            Sorted.derefBy(t1, t2, _3)
        }
        override def increment = {
            preIncrement
            Sorted.incrementBy(t1, t2, _3)
            ready
        }

        private def ready: Unit = {
            while (t1 && t2)  {
                if (_3(~t2, ~t1)) {
                    return
                } else if (_3(~t1, ~t2)) {
                    return
                } else {
                    t1.++
                    t2.++
                }
            }
        }
    }
}
