

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package toy; package nestedimplicittest


class L
object L {
    implicit def toR(from: L): R = new R
}

class R

class Wrap[A](x: A)

class NestedImplicitTest {

    val k1: Wrap[R] = new Wrap[R](new L) // OK

    val k2: Wrap[R] = new Wrap(new L) // OK!! (Where in SLS is this specified?, but it must work: e.g., assume Wrap is scala.FunctionN.)

    val k3 = new Wrap(new L) // k3: Wrap[L]
    // val k4: Wrap[R] = k3 // so, No

}
