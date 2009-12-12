

package madatest; package defecttest; package orderedorderingtest



class A

object A extends math.LowPriorityOrderingImplicits {

   implicit val k: Ordering[A] = throw new Error

}

class OrderedOrderingTest {
    def testTrivial(off: Int) = foo
    def foo(implicit ord: Ordering[A]) = ()
}

/*
E:\Application\maven-projects\mada\src\test\scala\madatest\defect\OrderedOrderingTest.scala:16: error: ambiguous implicit values:
 both method ordered in trait LowPriorityOrderingImplicits of type [A <: Ordered[A]]Ordering[A]
 and value k in object A of type => Ordering[madatest.defecttest.orderedorderingtest.A]
 match expected type Ordering[madatest.defecttest.orderedorderingtest.A]
    foo
    ^
*/



/*

class MySeq[T]

object MySeq {

   implicit def eligible[T](implicit ord: Ordering[T]): Ordering[MySeq[T]] = throw new Error

}

class OrderedOrderingTest {

    def testTrivial(off: Int): Unit = {
        sort(new MySeq[MySeq[Int]])
    }

    def sort[T](x: MySeq[T])(implicit ord: Ordering[T]): Unit = ()

}
*/
