

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package util


trait EvaluationStrategy {
    def install[R](to: Function0[R]): Function0[R]
}
