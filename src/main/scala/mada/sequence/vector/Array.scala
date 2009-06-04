

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.sequence.vector


import java.util.Arrays


case class FromArray[A](_1: Array[A]) extends Vector[A] {
    override def start = 0
    override def end = _1.length
    override def apply(i: Int) = _1(i)
    override def update(i: Int, e: A) = _1(i) = e

//    This requires IntFromArray for correct overload resolution.
//    override def sortBy(lt: compare.Func[A]) = { Arrays.sort(_1, LessComparator(lt)); this }
}
