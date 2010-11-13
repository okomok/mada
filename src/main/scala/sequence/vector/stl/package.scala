

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


/**
 * Port of <a href="http://www.sgi.com/tech/stl/">STL</a> algorithms.
 * Mainly used to implement <code>mada.Vector</code>.<p/>
 *
 * <code>mada.stl</code> works only on <code>Vector</code> as RandomAccessIterator.
 * (Java is heap-friendly, so ForwardIterator algorithms would be too slow.)
 * These algorithms (ironically) take region arguments as STL does to eliminate intermediate vector objects.<p/>
 *
 * Scala vs STL:
 * <ul>
 * <li/><code>Iterable</code>: SinglePassRange+Forward"Begin"Iterator
 * <li/><code>Collection</code>: ...maybe unneeded.
 * <li/><code>Seq</code>: ...maybe unneeded.
 * <li/><code>List</code>: ForwardRange
 * <li/><code>Stream</code>: ForwardRange
 * <li/><code>RandomAccessSeq</code>: RandomAccessRange
 * </ul>
 *
 * @see <a href="http://www.sgi.com/tech/stl/">STL</a>
 * @see <a href="http://www.stanford.edu/group/coursework/docsTech/jgl/">JGL</a>
 */
package object stl {

    def accumulate[A, B](v: Vector[A], first: Int, last: Int)(init: B)(binary_op: (B, A) => B): B = Accumulate(v, first, last, init, binary_op)

    def adjacentFind[A](v: Vector[A], first: Int, last: Int): Int = AdjacentFind(v, first, last)
    def adjacentFindIf[A](v: Vector[A], first: Int, last: Int)(binary_pred: (A, A) => Boolean): Int = AdjacentFind(v, first, last, binary_pred)

    def lowerBound[A](v: Vector[A], first: Int, last: Int, value: A)(implicit c: Ordering[A]): Int = LowerBound(v, first, last, value, c)
    def upperBound[A](v: Vector[A], first: Int, last: Int, value: A)(implicit c: Ordering[A]): Int = UpperBound(v, first, last, value, c)

    def copy[A](v : Vector[A], first: Int, last: Int)(^ : Vector[_ >: A], result: Int): Int = Copy(v, first, last, ^, result)
    def copyIf[A](v : Vector[A], first: Int, last: Int)(^ : Vector[_ >: A], result: Int)(pred: A => Boolean): Int = CopyIf(v, first, last, ^, result, pred)

    def copyBackward[A](v : Vector[A], first: Int, last: Int)(^ : Vector[_ >: A], result: Int): Int = CopyBackward(v, first, last, ^, result)

    def count[A](v: Vector[A], first: Int, last: Int)(e: Any): Int = Count(v, first, last, e)
    def countIf[A](v: Vector[A], first: Int, last: Int)(pred: A => Boolean): Int = CountIf(v, first, last, pred)

    def minElement[A](v: Vector[A], first: Int, last: Int)(implicit c: Ordering[A]): Int = MinElement(v, first, last, c)
    def maxElement[A](v: Vector[A], first: Int, last: Int)(implicit c: Ordering[A]): Int = MaxElement(v, first, last, c)

    def median[A](x: A, y: A, z: A)(implicit c: Ordering[A]): A = Median(x, y, z, c)

    def equal[A1, A2](v1: Vector[A1], first1: Int, last1: Int)(v2: Vector[A2], first2: Int): Boolean = Equal(v1, first1, last1, v2, first2)
    def equalIf[A1, A2](v1: Vector[A1], first1: Int, last1: Int)(v2: Vector[A2], first2: Int)(binary_pred: (A1, A2) => Boolean): Boolean = Equal(v1, first1, last1, v2, first2, binary_pred)

    def fill[A](v: Vector[A], first: Int, last: Int)(value: A): Unit = Fill(v, first, last, value)

    def find[A](v: Vector[A], first: Int, last: Int, value: Any): Int = Find(v, first, last, value)
    def findIf[A](v: Vector[A], first: Int, last: Int)(pred: A => Boolean): Int = FindIf(v, first, last, pred)

    def forEach[A, F <: (A => Any)](v: Vector[A], first: Int, last: Int, f: F): F = ForEach(v, first, last, f)

    def generate[A](v : Vector[A], first: Int, last: Int)(gen: Unit => A): Unit = Generate(v, first, last, gen)
    def generateN[A](^ : Vector[A], first: Int, n: Int)(gen: Unit => A): Unit = GenerateN(^, first, n, gen)

    def lexicographicalCompare[A](v1: Vector[A], first1: Int, __last1: Int)(v2: Vector[A], first2: Int, __last2: Int)(implicit c: Ordering[A]): Boolean = LexicographicalCompare(v1, first1, __last1, v2, first2, __last2, c)
    def lexicographicalCompare3way[A](v1: Vector[A], first1: Int, __last1: Int)(v2: Vector[A], first2: Int, __last2: Int)(implicit c: Ordering[A]): Int = LexicographicalCompare3way(v1, first1, __last1, v2, first2, __last2, c)

    def merge[A](v1 : Vector[A], first1: Int, last1: Int)(v2 : Vector[A], first2: Int, last2: Int)(^ : Vector[A], result: Int)(implicit c: Ordering[A]): Int = Merge(v1, first1, last1, v2, first2, last2, ^, result, c)

    def partialSort[A](v: Vector[A], first: Int, middle: Int, last: Int)(implicit c: Ordering[A]): Unit = PartialSort(v, first, middle, last, c)

    def pushHeap[A](v: Vector[A], first: Int, last: Int)(implicit c: Ordering[A]): Unit = PushHeap(v, first, last, c)
    def popHeap[A](v: Vector[A], first: Int, last: Int)(implicit c: Ordering[A]): Unit = PopHeap(v, first, last, c)
    def makeHeap[A](v: Vector[A], first: Int, last: Int)(implicit c: Ordering[A]): Unit = MakeHeap(v, first, last, c)
    def sortHeap[A](v: Vector[A], first: Int, last: Int)(implicit c: Ordering[A]): Unit = SortHeap(v, first, last, c)
    def isHeap[A](v: Vector[A], first: Int, last: Int)(implicit c: Ordering[A]): Unit = IsHeap(v, first, last, c)

    def iterSwap[A](v1: Vector[A], i1: Int)(v2: Vector[A], i2: Int): Unit = IterSwap(v1, i1, v2, i2)

    def randomShuffle[A](v: Vector[A], first: Int, last: Int): Unit = RandomShuffle(v, first, last)
    def randomShuffleBy[A](v: Vector[A], first: Int, last: Int)(rand: Int => Int): Unit = RandomShuffle(v, first, last, rand)

    def randomSample[A](v : Vector[A], first: Int, last: Int)(^ : Vector[_ >: A], out_first: Int, out_last: Int): Int = RandomSample(v, first, last, ^, out_first, out_last)
    def randomSampleBy[A](v : Vector[A], first: Int, last: Int)(^ : Vector[_ >: A], out_first: Int, out_last: Int)(rand: Int => Int): Int = RandomSample(v, first, last, ^, out_first, out_last, rand)

    def randomSampleN[A](v : Vector[A], first: Int, last: Int)(^ : Vector[_ >: A], out_ite: Int, n: Int): Int = RandomSampleN(v, first, last, ^, out_ite, n)
    def randomSampleNBy[A](v : Vector[A], first: Int, last: Int)(^ : Vector[_ >: A], out_ite: Int, n: Int)(rand: Int => Int): Int = RandomSampleN(v, first, last, ^, out_ite, n, rand)

    def remove[A](v: Vector[A], first: Int, last: Int)(e: Any): Int = Remove(v, first, last, e)
    def removeIf[A](v: Vector[A], first: Int, last: Int)(pred: A => Boolean): Int = RemoveIf(v, first, last, pred)

    def removeCopy[A](v : Vector[A], first: Int, last: Int)(^ : Vector[_ >: A], result: Int)(e: Any): Int = RemoveCopy(v, first, last, ^, result, e)
    def removeCopyIf[A](v : Vector[A], first: Int, last: Int)(^ : Vector[_ >: A], result: Int)(pred: A => Boolean): Int = RemoveCopyIf(v, first, last, ^, result, pred)

    def replace[A](v: Vector[A], first: Int, last: Int)(old_value: Any, new_value: A): Unit = Replace(v, first, last, old_value, new_value)
    def replaceIf[A](v: Vector[A], first: Int, last: Int)(pred: A => Boolean, new_value: A): Unit = ReplaceIf(v, first, last, pred, new_value)

    def replaceCopy[A](v : Vector[A], first: Int, last: Int)(^ : Vector[_ >: A], result: Int, old_value: Any, new_value: A): Int = ReplaceCopy(v, first, last, ^, result, old_value, new_value)
    def replaceCopyIf[A](v : Vector[A], first: Int, last: Int)(^ : Vector[_ >: A], result: Int)(pred: A => Boolean, new_value: A): Int = ReplaceCopyIf(v, first, last, ^, result, pred, new_value)

    def reverse[A](v: Vector[A], first: Int, last: Int): Unit = Reverse(v, first, last)

    def sort[A](v: Vector[A], first: Int, last: Int)(implicit c: Ordering[A]): Unit = Sort(v, first, last, c)
    def stableSort[A](v: Vector[A], first: Int, last: Int)(implicit c: Ordering[A]): Unit = StableSort(v, first, last, c)
    def isSorted[A](v: Vector[A], first: Int, last: Int)(implicit c: Ordering[A]): Boolean = IsSorted(v, first, last, c)

    def swapRanges[A](v1: Vector[A], first1: Int, last1: Int)(v2: Vector[A], first2: Int): Int = SwapRanges(v1, first1, last1, v2, first2)

    def transform[A, B](v : Vector[A], first: Int, last: Int)(^ : Vector[B], result: Int)(opr: A => B): Int = Transform(v, first, last, ^, result, opr)
    def transformZip[A, B, C](v1 : Vector[A], first1: Int, last1: Int)(v2 : Vector[B], first2: Int)(^ : Vector[C], result: Int)(binary_op: (A, B) => C): Int = Transform(v1, first1, last1, v2, first2, ^, result, binary_op)

    def unique[A](v: Vector[A], first: Int, last: Int): Int = Unique(v, first, last)
    def uniqueIf[A](v: Vector[A], first: Int, last: Int)(binary_pred: (A, A) => Boolean): Int = Unique(v, first, last, binary_pred)

    def uniqueCopy[A](v : Vector[A], first: Int, last: Int)(^ : Vector[_ >: A], result: Int): Int = UniqueCopy(v, first, last, ^, result)
    def uniqueCopyIf[A, B >: A](v : Vector[A], first: Int, last: Int)(^ : Vector[B], result: Int)(binary_pred: (A, B) => Boolean): Int = UniqueCopy(v, first, last, ^, result, binary_pred)

    /**
     * Creates <code>OutputVector</code> in which <code>output(e)</code> calls <code>f(e)</code>.
     */
    def outputBy[A](f: A => Unit): Vector[A] = OutputBy(f)

}
