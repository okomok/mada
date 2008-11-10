
package mada.range


object Conversions {
    implicit def madaRangeFromArray[A](from: Array[A]) = FromArray(from)
    implicit def madaRangeFromArrayList[A](from: java.util.ArrayList[A]) = FromArrayList(from)
    implicit def madaRangeFromIterator[A](from: Iterator[A]) = FromIterator(from)

    implicit def madaRangeToArray[A](from: Range[A]) = from.toArray
    implicit def madaRangeToArrayList[A](from: Range[A]) = from.toArrayList
    implicit def madaRangeToIterator[A](from: Range[A]) = from.toIterator
}
