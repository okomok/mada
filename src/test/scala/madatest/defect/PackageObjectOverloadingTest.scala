

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.defecttest


package object overloading {

    def foo(n: Int, m: Int): Unit = ()
    def foo(n: Int, u: Unit): Unit = assert(false)

    def bar(f: (Int, Int) => Unit): Unit = ()
    def bar(f: (Int, Int, Int) => Unit): Unit = ()

}

class PackageObjectOverloadingTest {
    def buz(n: Int, m: Int): Unit = ()

    def testTrivial(off: Int) = {
        overloading.foo(1, 2) // assertion failed.
        // overloading.bar(buz _) // doesn't compile.
    }
}
