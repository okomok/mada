

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.meta


    class DocTest {

    // run-time world

        // boolean value
        assert(true)

        // method
        def increment(n: Int) = n + 1

        // trait (cut-n-pasted from scala.Product1)
        trait Product1[+T1] { // takes type parameter.
            def _1(): T1 // abstract method
        }

        // value
        val p = new Product1[Int] { // passes type argument.
            override def _1() = 7 // implements method.
        }

        // another method
        def getAndInc(x: Product1[Int]) = x._1() + 1
        assert(getAndInc(p) == 8)

        // converts method to function(value).
        val inc = increment _
        assert(inc.apply(3) == 4) // function invocation

    // compile-time world

        import mada.Meta._

        // meta boolean value
        assert[`true`]

        // metamethod
        type mincrement[n <: Nat] = n#increment[_] // metamethod invocation by `#`

        // metatrait
        trait MProduct1 extends Object {
            type _T1 <: Object // takes metatype parameter.
            type _1[_] <: _T1 // abstract metamethod
        }

        // metavalue
        trait mp extends MProduct1 {
            override type _T1 = Nat // passes metatype argument.
            override type _1[_] = _7N // implements metamethod.
        }

        // another metamethod
        type mgetAndInc[x <: MProduct1 { type _1[_] <: Nat }] = x#_1[_]#increment[_]
        assert[mgetAndInc[mp] == _8N]

        // converts metamethod to metafunction(metavalue).
        trait minc extends quote1[Nat, Nat, mincrement]
        assert[minc#apply1[_3N] == _4N] // metafunction invocation

        def testTrivial: Unit = ()
    }
