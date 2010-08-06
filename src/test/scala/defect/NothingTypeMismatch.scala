

package com.github.okomok.madatest;
package defecttest; package nothingtypemismatch

trait Bool {
    type not <: Bool
}

trait True extends Bool {
    type not = False
}

trait False extends Bool {
    type not = True
}

trait Nat {
    type self <: Nat
    type dec <: Nat
    type <[that <: Nat] <: Bool
    type <=[that <: Nat] <: Bool

    type toPeano <: Peano
}


trait Peano extends Nat {
    type self <: Peano
    type dec <: Peano
    type <[that <: Nat] = that# <=[self]#not

    type callLt[that <: Nat] = CallLt[self, that#toPeano]
}

trait Zero extends Peano {
    type self = Zero
    type dec = Nothing
    type <=[that <: Nat] = True
}

trait Succ[n <: Peano] extends Peano {
    type self = Succ[n]
    type dec = n
    type <=[that <: Nat] = n# <=[that#dec]
}

trait CallLt[n <: Peano, m <: Peano] {
    type apply = n# <[m]
}
