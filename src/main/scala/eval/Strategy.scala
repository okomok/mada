

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package eval


trait Strategy {
    def apply[R](f: Function0[R]): Function0[R]
}
