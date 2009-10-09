

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest; package toy; package selftype


trait Foo {
    self: Bar.type =>
}

object Bar extends Foo

class SelfTypeTest {
    def testTrivial: Unit = {
        try {
           // new Foo {} // doesn't compile.
        } finally {
        }
    }
}
