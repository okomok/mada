

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


package object ordering extends ordering.Common {

    @aliasOf("dual.Ordering")
     val Ordering = dual.Ordering
    type Ordering = dual.Ordering

}
