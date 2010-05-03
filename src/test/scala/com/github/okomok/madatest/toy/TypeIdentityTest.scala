

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package toy; package typeidentitytest


import com.github.okomok.mada

//import junit.framework.Assert._

import mada.meta.assertSame
import mada.meta




// nullary

sealed trait Func {
    type result
}

sealed trait FuncInt extends Func {
    override type result = Int
}

sealed trait id[f <: Func] {
    type what = f#result
}


// unary

sealed trait Func1 {
    type apply[x]
}

sealed trait Fwd1[f <: Func1] extends Func1 {
    override type apply[x] = f#apply[x]
}

sealed trait Func1Id extends Func1 {
    override type apply[x] = x
}


class TypeIdentityTest {



    assertSame[Func1Id#apply[Double], Double]
    assertSame[Fwd1[Func1Id]#apply[Double], Double] // ok

    type callFwd1[f <: Func1, x] = Fwd1[f]#apply[x]
    assertSame[callFwd1[Func1Id, Double], Double] // ok

    type callFwd111[f <: Func1, x] = Fwd1[Fwd1[Fwd1[f]]]#apply[x]
    assertSame[callFwd111[Func1Id, Double], Double] // ok


    assertSame[FuncInt#result, Int] // ok

    assertSame[id[FuncInt]#what, Int]
    type callid[f <: Func] = id[f]#what
    assertSame[callid[FuncInt], Int]


    assertSame[meta.Succ[mada.meta._1N]#equals[meta._2N]#not, meta.`false`]

}

