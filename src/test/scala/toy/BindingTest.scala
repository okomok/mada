

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package toy; package binding


object Foo {

    def some(): Unit = ()

}

object Bar {

    def some(): Unit = ()

}


object Buz {
    val Foo1 = Foo
    val Foo2 = Foo
}

/* ambiguous
import Foo._
import Bar._
*/

// maybe guaranteed to work?
import Buz.Foo1._
import Buz.Foo2._


class BindingTezt {
    some()
}
