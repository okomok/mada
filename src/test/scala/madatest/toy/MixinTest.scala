

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package madatest.toy.mixin


import junit.framework.Assert._

/*
trait Foo {
    def foo: Int
    def bar: Int = foo + 1 // client
    final def buz = bar // alias
}


trait MixinFoo extends Foo {
    override def bar: Int = foo + 1 // client
}


object MyFoo extends Foo {
    override def foo = 9
}


trait FooProxy extends Foo {
    def self: Foo
    override def foo = self.foo
    override def bar = self.bar
}


object OurFoo extends FooProxy {
    override def self = MyFoo
    override def foo = 100
}

object YourFoo extends FooProxy with MixinFoo {
    override def self = MyFoo
    override def foo = 100
}
*/




trait Foo  {
    def foo: Int
    def far: Int
    def bar: Int = foo + 1 // client
    final def buz = bar // alias
}


object MyFoo extends Foo {
    override def foo = 9
    override def far = 4
}


trait FooMinimalProxy extends Foo {
    def self: Foo
    override def foo = self.foo
    override def far = self.far
}

trait FooProxy extends Foo with FooMinimalProxy {
    override def bar = self.bar
}


object OurFoo extends FooProxy {
    override def self = MyFoo
    override def foo = 100
}

object YourFoo extends Foo with FooMinimalProxy {
    override def self = MyFoo
    override def foo = 100
    // def bar = Foo.bar (not self.bar)
}


class MixinTest {
    def testTrivial {
        assertEquals(9, MyFoo.foo)
        assertEquals(4, MyFoo.far)
        assertEquals(10, MyFoo.bar)
        assertEquals(10, MyFoo.buz)

        assertEquals(100, OurFoo.foo)
        assertEquals(4, OurFoo.far)
        assertEquals(10, OurFoo.bar)
        assertEquals(10, OurFoo.buz)

        assertEquals(100, YourFoo.foo)
        assertEquals(4, YourFoo.far)
        assertEquals(101, YourFoo.bar)
        assertEquals(101, YourFoo.buz)
    }
}

