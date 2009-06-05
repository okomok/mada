

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


// CharSequence

case class FromJCharSequence(_1: java.lang.CharSequence) extends Forwarder[Char] {
    override protected val delegate: Vector[Char] = _1 match {
        case ToJCharSequence(from) => from // from-to fusion
        case _ => new _FromJCharSequence(_1)
    }
}

private class _FromJCharSequence(_1: java.lang.CharSequence) extends Vector[Char] {
    override def start = 0
    override def end = _1.length
    override def apply(i: Int) = _1.charAt(i)

    override def _toJCharSequence(_this: Vector[Char]) = _1 // to-from fusion
}

case class ToJCharSequence(_1: Vector[Char]) extends java.lang.CharSequence {
    override def charAt(index: Int) = _1.nth(index)
    override def length = _1.nth.size
    override def subSequence(start: Int, end: Int): java.lang.CharSequence = new ToJCharSequence(_1.nth(start, end))
    override def toString = _1.nth.stringize
}


// Iterable

case class ToJIterable[A](_1: Vector[A]) extends java.lang.Iterable[A] {
    override def iterator: java.util.Iterator[A] = new _JListIterator(_1)
}

private class _JListIterator[A](v: Vector[A]) extends java.util.ListIterator[A] {
    private var cur = v.start

    override def add(e: A) = throw new UnsupportedOperationException
    override def hasNext = cur != v.end
    override def hasPrevious = cur != v.start

    override def next = {
        if (!hasNext) {
            throw new NoSuchElementException("next")
        }
        val tmp = v(cur)
        cur += 1
        tmp
    }

    override def nextIndex = cur

    override def previous = {
        if (!hasPrevious) {
            throw new NoSuchElementException("previous")
        }
        cur -= 1
        v(cur)
    }

    override def previousIndex = cur - 1
    override def remove = throw new UnsupportedOperationException("JListIterator.remove")
    override def set(e: A) = throw new UnsupportedOperationException("JListIterator.set")
}

case class FromJIterable[A](_1: java.lang.Iterable[A]) extends Forwarder[A] {
    override protected lazy val delegate = {
        val r = new java.util.ArrayList[A]
        val it = _1.iterator
        while (it.hasNext) {
            r.add(it.next)
        }
        vector.fromJList(r)
    }
}


// List

case class FromJList[A](_1: java.util.List[A]) extends Vector[A] {
    override def start = 0
    override def end = _1.size
    override def apply(i: Int) = _1.get(i)
    override def update(i: Int, e: A) = _1.set(i, e)

    override def sortBy(lt: compare.Func[A]) = {
        java.util.Collections.sort(_1, compare.from(lt).toJComparator)
        this
    }

    override def stableSortBy(lt: compare.Func[A]) = {
        java.util.Collections.sort(_1, compare.from(lt).toJComparator)
        this
    }
}
