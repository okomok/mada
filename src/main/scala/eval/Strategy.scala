

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package eval


trait Strategy {
    def apply[R](f: ByName[R]): Function0[R]
    def apply[R](f: => R): Function0[R] // needed: http://lampsvn.epfl.ch/trac/scala/ticket/3237
}
