

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.toy.implicitscope


object Foo {
    var found = false
    implicit def toBar(f: Foo): Bar = {
        found = true
        new Bar
    }

    implicit def fromBar(b: Bar): Foo = {
        found = true
        new Foo
    }
}

class Foo

class Bar


class ImplicitScopeTest {
    def testTo: Unit = {
        Foo.found = false
        useBar(new Foo)
        junit.framework.Assert.assertTrue(Foo.found)
    }

    def testFrom: Unit = {
        Foo.found = false
        useFoo(new Bar)
        junit.framework.Assert.assertTrue(Foo.found)
    }

    def useBar(b: Bar): Unit = ()
    def useFoo(f: Foo): Unit = ()
}
