

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest
package toy; package modernseqtest


import junit.framework.Assert._


trait MyFunctor[+A] {
    def map[B](f: A => B): map[B]
    type map[B] <: MyFunctor[B]
}

class MyVector[A] extends MyFunctor[A] {
    override def map[B](f: A => B): map[B] = new MyVector[B]
    override type map[B] = MyVector[B]
}


class ModernSeqTezt {
    def callMap[s <: MyFunctor[A], A, B](s: s, f: A => B): s#map[B] = s.map(f)

    def testTrivial {
        val v = new MyVector[Int]
        val w: MyVector[String] = v.map(x => x.toString)
        val z: MyVector[String] = callMap(v, (x: Int) => x.toString)
        ()
    }
}

