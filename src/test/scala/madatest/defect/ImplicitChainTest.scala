

package madatest; package defecttest; package implicitchaintest



package mmm {

    package object compare {
        type Func[-T] = Function2[T, T, Boolean]

        type GetOrdered[T] = Function1[T, Ordered[T]]

        def fromGetOrdered[A](implicit from: GetOrdered[A]): Compare[A] = new Compare[A]{ override def apply(x: A, y: A) = true }
    }

    import compare._

    trait Compare[-A] extends Func[A] {
    }


    object Compare {
        implicit def madaCompareFromGetOrdered[A](implicit from: GetOrdered[A]): Compare[A] = fromGetOrdered(from)
    }


    package object peg {
        def range[A](i: A, j: A)(implicit c: Compare[A]): Peg[A] = new Peg[A]{}
    }

    trait Peg[A]

}

class ImplicitChainTest {
    def testTrivial: Unit = {
        mmm.peg.range('0', '9')
        mmm.peg.range('0', '9')
        ()
    }
}
