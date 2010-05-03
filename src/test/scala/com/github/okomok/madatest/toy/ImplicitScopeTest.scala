

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package toy; package implicitscope


package my {

    trait FooCompatibles {
        var found = false
        implicit def toBar[A](f: Foo[A]): Bar[A] = {
            found = true
            new Bar[A]
        }

        implicit def fromBar[A](b: Bar[A]): Foo[A] = {
            found = true
            new Foo[A]
        }

        val Compatibles = this
    }

    object Foo extends FooCompatibles {
        val Strong = foo.Strong
    }

    package foo {
        object Strong {
            var found = false

            implicit def toBar[A](f: Foo[A]): Bar[A] = {
                found = true
                new Bar[A]
            }

            implicit def fromBar[A](b: Bar[A]): Foo[A] = {
                found = true
                new Foo[A]
            }
        }
    }

    class Foo[A] {
        implicit def toBuz: Buz = {
            Foo.found = true
            new Buz
        }
    }

    class Bar[A]

    class Buz

}


class ImplicitScopeTest {

    import my._
    import my.Foo.Compatibles._


    def testTo: Unit = {
        Foo.found = false
        useBar(new Foo[Int])
        junit.framework.Assert.assertTrue(Foo.found)
    }

    def testFrom: Unit = {
        Foo.found = false
        useFoo(new Bar[Int])
        junit.framework.Assert.assertTrue(Foo.found)
    }


    def testToStrong: Unit = {
        import my.Foo.Strong._ // hmm, maybe should fail to compile...
        my.Foo.Strong.found = false
        useBar(new Foo[Int])
        junit.framework.Assert.assertTrue(my.Foo.Strong.found)
    }

    def testFromStrong: Unit = {
        import my.Foo.Strong._
        my.Foo.Strong.found = false
        useFoo(new Bar[Int])
        junit.framework.Assert.assertTrue(my.Foo.Strong.found)
    }

/*
    def testMethod: Unit = {
        Foo[A].found = false
        useBuz(new Foo[A])
        junit.framework.Assert.assertTrue(Foo[A].found)
    }
*/
    def useBar[A](b: Bar[A]): Unit = ()
    def useFoo[A](f: Foo[A]): Unit = ()
    def useBuz(b: Buz): Unit = ()
}
