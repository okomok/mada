

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada


/**
 * Port of <a href="http://www.sgi.com/tech/stl/">STL</a> algorithms.
 * Mainly used to implement <code>mada.Vector</code>.<p/>
 *
 * <code>mada.Stl</code> supports only <code>Vector</code> as RandomAccessIterator.
 * (Java is heap-friendly, so ForwardIterator algorithms would be too slow.)
 * These algorithms (ironically) take region arguments as STL does to eliminate intermediate vector objects.<p/>
 *
 * Scala vs STL:
 * <ul>
 * <li/><code>Iterator</code>: SinglePassRange
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
object Stl {
    import stl._
    import Compare.OrderedView

    def accumulate[A, B](v: Vector[A], first: Int, last: Int, init: B, binary_op: (B, A) => B): B = Accumulate(v, first, last, init, binary_op)

    def adjacentFind[A](v: Vector[A], first: Int, last: Int): Int = AdjacentFind(v, first, last)
    def adjacentFind[A](v: Vector[A], first: Int, last: Int, binary_pred: Compare.Type[A]): Int = AdjacentFind(v, first, last, binary_pred)

    def lowerBound[A](v: Vector[A], first: Int, last: Int, value: A)(implicit c: OrderedView[A]): Int = LowerBound(v, first, last, value)(c)
    def lowerBound[A](v: Vector[A], first: Int, last: Int, value: A, comp: Compare.Type[A]): Int = LowerBound(v, first, last, value, comp)
    def upperBound[A](v: Vector[A], first: Int, last: Int, value: A)(implicit c: OrderedView[A]): Int = UpperBound(v, first, last, value)(c)
    def upperBound[A](v: Vector[A], first: Int, last: Int, value: A, comp: Compare.Type[A]): Int = UpperBound(v, first, last, value, comp)

    def copy[A, B >: A](v : Vector[A], first: Int, last: Int, ^ : Vector[B], result: Int): Int = Copy(v, first, last, ^, result)
    def copyIf[A, B >: A](v : Vector[A], first: Int, last: Int, ^ : Vector[B], result: Int, pred: A => Boolean): Int = CopyIf(v, first, last, ^, result, pred)

    def copyBackward[A, B >: A](v : Vector[A], first: Int, last: Int, ^ : Vector[B], result: Int): Int = CopyBackward(v, first, last, ^, result)

    def count[A](v: Vector[A], first: Int, last: Int, e: Any): Int = Count(v, first, last, e)
    def countIf[A](v: Vector[A], first: Int, last: Int, pred: A => Boolean): Int = CountIf(v, first, last, pred)

    def minElement[A](v: Vector[A], first: Int, last: Int)(implicit c: OrderedView[A]): Int = MinElement(v, first, last)(c)
    def minElement[A](v: Vector[A], first: Int, last: Int, comp: Compare.Type[A]): Int = MinElement(v, first, last, comp)
    def maxElement[A](v: Vector[A], first: Int, last: Int)(implicit c: OrderedView[A]): Int = MaxElement(v, first, last)(c)
    def maxElement[A](v: Vector[A], first: Int, last: Int, comp: Compare.Type[A]): Int = MaxElement(v, first, last, comp)

    def median[A](x: A, y: A, z: A)(implicit c: OrderedView[A]): A = Median(x, y, z)(c)
    def median[A](x: A, y: A, z: A, comp: Compare.Type[A]): A = Median(x, y, z, comp)

    def equal[A1, A2](v1: Vector[A1], first1: Int, last1: Int, v2: Vector[A2], first2: Int): Boolean = Equal(v1, first1, last1, v2, first2)
    def equal[A1, A2](v1: Vector[A1], first1: Int, last1: Int, v2: Vector[A2], first2: Int, binary_pred: (A1, A2) => Boolean): Boolean = Equal(v1, first1, last1, v2, first2, binary_pred)

    def fill[A](v: Vector[A], first: Int, last: Int, value: A): Unit = Fill(v, first, last, value)

    def find[A](v: Vector[A], first: Int, last: Int, value: Any): Int = Find(v, first, last, value)
    def find[A](v: Vector[A], first: Int, last: Int, pred: A => Boolean): Int = Find(v, first, last, pred)

    def forEach[A, F <: (A => Any)](v: Vector[A], first: Int, last: Int, f: F): F = ForEach(v, first, last, f)

    def generate[A](v : Vector[A], first: Int, last: Int, gen: Unit => A): Unit = Generate(v, first, last, gen)
    def generateN[A](^ : Vector[A], first: Int, n: Int, gen: Unit => A): Unit = GenerateN(^, first, n, gen)

    def lexicographicalCompare[A](v1: Vector[A], first1: Int, __last1: Int, v2: Vector[A], first2: Int, __last2: Int)(implicit c: OrderedView[A]): Boolean = LexicographicalCompare(v1, first1, __last1, v2, first2, __last2)(c)
    def lexicographicalCompare[A](v1: Vector[A], first1: Int, __last1: Int, v2: Vector[A], first2: Int, __last2: Int, __comp: Compare.Type[A]): Boolean = LexicographicalCompare(v1, first1, __last1, v2, first2, __last2, __comp)
    def lexicographicalCompare3way[A](v1: Vector[A], first1: Int, __last1: Int, v2: Vector[A], first2: Int, __last2: Int)(implicit c: OrderedView[A]): Int = LexicographicalCompare3way(v1, first1, __last1, v2, first2, __last2)(c)
    def lexicographicalCompare3way[A](v1: Vector[A], first1: Int, __last1: Int, v2: Vector[A], first2: Int, __last2: Int, __comp: Compare.Type[A]): Int = LexicographicalCompare3way(v1, first1, __last1, v2, first2, __last2, __comp)

    def partialSort[A](v: Vector[A], first: Int, middle: Int, last: Int)(implicit c: OrderedView[A]): Unit = PartialSort(v, first, middle, last)(c)
    def partialSort[A](v: Vector[A], first: Int, middle: Int, last: Int, comp: Compare.Type[A]): Unit = PartialSort(v, first, middle, last, comp)

    def pushHeap[A](v: Vector[A], first: Int, last: Int)(implicit c: OrderedView[A]): Unit = PushHeap(v, first, last)(c)
    def pushHeap[A](v: Vector[A], first: Int, last: Int, comp: Compare.Type[A]): Unit = PushHeap(v, first, last, comp)

    def popHeap[A](v: Vector[A], first: Int, last: Int)(implicit c: OrderedView[A]): Unit = PopHeap(v, first, last)(c)
    def popHeap[A](v: Vector[A], first: Int, last: Int, comp: Compare.Type[A]): Unit = PopHeap(v, first, last, comp)

    def makeHeap[A](v: Vector[A], first: Int, last: Int)(implicit c: OrderedView[A]): Unit = MakeHeap(v, first, last)(c)
    def makeHeap[A](v: Vector[A], first: Int, last: Int, comp: Compare.Type[A]): Unit = MakeHeap(v, first, last, comp)

    def sortHeap[A](v: Vector[A], first: Int, last: Int)(implicit c: OrderedView[A]): Unit = SortHeap(v, first, last)(c)
    def sortHeap[A](v: Vector[A], first: Int, last: Int, comp: Compare.Type[A]): Unit = SortHeap(v, first, last, comp)

    def isHeap[A](v: Vector[A], first: Int, last: Int)(implicit c: OrderedView[A]): Unit = IsHeap(v, first, last)(c)
    def isHeap[A](v: Vector[A], first: Int, last: Int, comp: Compare.Type[A]): Boolean = IsHeap(v, first, last, comp)

    def iterSwap[A](v1: Vector[A], i1: Int, v2: Vector[A], i2: Int): Unit = IterSwap(v1, i1, v2, i2)

    def randomShuffle[A](v: Vector[A], first: Int, last: Int): Unit = RandomShuffle(v, first, last)
    def randomShuffle[A](v: Vector[A], first: Int, last: Int, rand: Int => Int): Unit = RandomShuffle(v, first, last, rand)

    def randomSample[A, B >: A](v : Vector[A], first: Int, last: Int, ^ : Vector[B], out_first: Int, out_last: Int): Int = RandomSample(v, first, last, ^, out_first, out_last)
    def randomSample[A, B >: A](v : Vector[A], first: Int, last: Int, ^ : Vector[B], out_first: Int, out_last: Int, rand: Int => Int): Int = RandomSample(v, first, last, ^, out_first, out_last, rand)

    def randomSampleN[A, B >: A](v : Vector[A], first: Int, last: Int, ^ : Vector[B], out_ite: Int, n: Int): Int = RandomSampleN(v, first, last, ^, out_ite, n)
    def randomSampleN[A, B >: A](v : Vector[A], first: Int, last: Int, ^ : Vector[B], out_ite: Int, n: Int, rand: Int => Int): Int = RandomSampleN(v, first, last, ^, out_ite, n, rand)

    def remove[A](v: Vector[A], first: Int, last: Int, e: Any): Int = Remove(v, first, last, e)
    def remove[A](v: Vector[A], first: Int, last: Int, pred: A => Boolean): Int = Remove(v, first, last, pred)

    def removeCopy[A, B >: A](v : Vector[A], first: Int, last: Int, ^ : Vector[B], result: Int, e: Any): Int = RemoveCopy(v, first, last, ^, result, e)
    def removeCopyIf[A, B >: A](v : Vector[A], first: Int, last: Int, ^ : Vector[B], result: Int, pred: A => Boolean): Int = RemoveCopyIf(v, first, last, ^, result, pred)

    def replace[A](v: Vector[A], first: Int, last: Int, old_value: Any, new_value: A): Unit = Replace(v, first, last, old_value, new_value)
    def replaceIf[A](v: Vector[A], first: Int, last: Int, pred: A => Boolean, new_value: A): Unit = ReplaceIf(v, first, last, pred, new_value)

    def replaceCopy[A, B >: A](v : Vector[A], first: Int, last: Int, ^ : Vector[B], result: Int, old_value: Any, new_value: A): Int = ReplaceCopy(v, first, last, ^, result, old_value, new_value)
    def replaceCopyIf[A, B >: A](v : Vector[A], first: Int, last: Int, ^ : Vector[B], result: Int, pred: A => Boolean, new_value: A): Int = ReplaceCopyIf(v, first, last, ^, result, pred, new_value)

    def reverse[A](v: Vector[A], first: Int, last: Int): Unit = Reverse(v, first, last)

    def sort[A](v: Vector[A], first: Int, last: Int)(implicit c: OrderedView[A]): Unit = Sort(v, first, last)(c)
    def sort[A](v: Vector[A], first: Int, last: Int, comp: Compare.Type[A]): Unit = Sort(v, first, last, comp)

    def isSorted[A](v: Vector[A], first: Int, last: Int)(implicit c: OrderedView[A]): Boolean = IsSorted(v, first, last)(c)
    def isSorted[A](v: Vector[A], first: Int, last: Int, comp: Compare.Type[A]): Boolean = IsSorted(v, first, last, comp)

    def swapRanges[A](v1: Vector[A], first1: Int, last1: Int, v2: Vector[A], first2: Int): Int = SwapRanges(v1, first1, last1, v2, first2)

    def transform[A, B](v : Vector[A], first: Int, last: Int, ^ : Vector[B], result: Int, opr: A => B): Int = Transform(v, first, last, ^, result, opr)
    def transform[A, B, C](v1 : Vector[A], first1: Int, last1: Int, v2 : Vector[B], first2: Int,  ^ : Vector[C], result: Int, binary_op: (A, B) => C): Int = Transform(v1, first1, last1, v2, first2, ^, result, binary_op)

    def unique[A](v: Vector[A], first: Int, last: Int): Int = Unique(v, first, last)
    def unique[A](v: Vector[A], first: Int, last: Int, binary_pred: Compare.Type[A]): Int = Unique(v, first, last, binary_pred)

    def uniqueCopy[A, B >: A](v : Vector[A], first: Int, last: Int, ^ : Vector[B], result: Int): Int = UniqueCopy(v, first, last, ^, result)
    def uniqueCopy[A, B >: A](v : Vector[A], first: Int, last: Int, ^ : Vector[B], result: Int, binary_pred: Functions.Predicate2[A, B]): Int = UniqueCopy(v, first, last, ^, result, binary_pred)

    /**
     * Alias of <code>stl.OutputVector</code>
     */
    type OutputVector[A] = stl.OutputVector[A]

    /**
     * Alias of <code>stl.OutputCounter</code>
     */
    type OutputCounter = stl.OutputCounter

    /**
     * Creates <code>OutputVector</code> in which <code>output(e)</code> calls <code>f(e)</code>.
     */
    def outputWith[A](f: A => Unit): Vector[A] = OutputWith(f)
}
