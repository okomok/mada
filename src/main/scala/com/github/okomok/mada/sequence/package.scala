

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada


package object sequence {

    @aliasOf("iterative.Iterative")
     val Iterative = iterative.Iterative
    type Iterative[+A] = iterative.Iterative[A]


    @aliasOf("iterator.Iterator")
     val Iterator = iterator.Iterator
    type Iterator[+A] = iterator.Iterator[A]

}
