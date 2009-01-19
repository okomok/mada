

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


object VectorStream {
    def apply[A](v: Vector[A]): Stream[A] = new VectorStream(v)
}

class VectorStream[A](v: Vector[A]) extends Stream.Definite[A] {
    override def isEmpty = v.isEmpty
    override def head = {
        if (isEmpty) {
            throw new NoSuchElementException("head of empty VectorStream")
        } else {
            v.head
        }
    }
    override def tail = _tail
    override protected def addDefinedElems(buf: StringBuilder, prefix: String) = {
        if (isEmpty) {
            buf
        } else {
            _tail.addDefinedElems(buf.append(prefix).append(head), ", ")
        }
    }

    // optimizations
    override def length = v.size.toInt
    override def elements = v.elements
    override def init = new VectorStream(v.init)
    override def apply(n: Int) = v(n)
    override def take(n: Int) = new VectorStream(v.take(n))
    override def takeWhile(p: A => Boolean) = new VectorStream(v.takeWhile(p))
    override def map[B](f: A => B) = new VectorStream(v.map(f))
    override def foldRight[B](z: B)(f: (A, B) => B) = v.foldRight(z)(f)
    override def reverse = new VectorStream(v.reverse)

    // addDefinedElems is protected.
    private lazy val _tail = {
        if (isEmpty) {
            throw new UnsupportedOperationException("tail of empty VectorStream")
        } else {
            new VectorStream(v.tail) // needs heap-allocation.
        }
    }
}
