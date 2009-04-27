

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.meta


    class DocTest {
        // boolean value
        assert(true)

        // method
        def increment(n: Int) = n + 1

        // trait (cut-n-pasted from scala.Product1)
        trait Product1[+T1] {
            def _1: T1 // abstract method
        }

        // value
        val p = new Product1[Int] { // passes type argument.
            override def _1 = 7 // implements method.
        }

        // another method
        def getAndInc(x: Product1[Int]) = x._1 + 1
        assert(getAndInc(p) == 8)

        // converts method to function(value).
        val inc = increment(_ : Int)

        // function invocation
        assert(inc.apply(3) == 4)

        def testTrivial: Unit = ()
    }

    class MetaDocTest {
        import mada.Meta._

        // meta boolean value
        assert[`true`]

        // metamethod
        type increment[n <: Nat] = n#increment // metamethod invocation by `#`

        // metatrait
        trait Product1 extends Object {
            type _1 <: Object // abstract metamethod
        }

        // metavalue
        trait p extends Product1 {
            override type _1 = _7N // implements metamethod.
        }

        // another metamethod
        type getAndInc[x <: Product1 { type _1 <: Nat }] = x#_1#increment
        assert[getAndInc[p] == _8N]

        // converts metamethod to metafunction(metavalue).
        trait inc extends quote1[increment, Nat]

        // metafunction invocation
        assert[inc#apply1[_3N] == _4N]

        def testTrivial: Unit = ()
    }
