

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


// See: Nats.scala
//      at http://www.assembla.com/wiki/show/metascala


@provider
trait Nats { this: Meta.type =>


    type _0 = Nat.zero
    type _1 = Nat.succ[_0]
    type _2 = Nat.succ[_1]
    type _3 = Nat.succ[_2]
    type _4 = Nat.succ[_3]
    type _5 = Nat.succ[_4]
    type _6 = Nat.succ[_5]
    type _7 = Nat.succ[_6]
    type _8 = Nat.succ[_7]
    type _9 = Nat.succ[_8]
    type _10 = Nat.succ[_9]


    trait Nat extends Operatable {
        final type equals[that <: Nat] = identity#equals[that#identity]
        final override type Operand_== = Nat
        final override type operator_==[that <: Nat] = equals[that]

        type increment <: Nat

        type add[that <: Nat] <: Nat
        final override type Operand_+ = Nat
        final override type operator_+[that <: Nat] = add[that]

        type multiply[that <: Nat] <: Nat

        type identity <: Nat.Identity
        type accept[v <: Nat.Visitor] <: v#Result // More generic algorithms (fold etc) won't work.
    }


    object Nat {

        sealed trait zero extends Nat {
            override type increment = succ[zero]
            override type add[that <: Nat] = that
            override type multiply[that <: Nat] = zero
            override type identity = Identity._0
            override type accept[v <: Visitor] = v#visitZero
        }

        sealed trait succ[n <: Nat] extends Nat {
            override type increment = succ[succ[n]]
            override type add[that <: Nat] = succ[n#add[that]]
            override type multiply[that <: Nat] = n#multiply[that]#add[that]
            override type identity = n#identity#increment
            override type accept[v <: Visitor] = v#visitSucc[n]
        }

        trait Visitor {
            type Result
            type visitZero <: Result
            type visitSucc[n <: Nat] <: Result
        }

        sealed trait Identity {
            type increment <: Identity
            type equals[that <: Identity] <: Boolean

            private[mada] type is0 <: Boolean
            private[mada] type is1 <: Boolean
            private[mada] type is2 <: Boolean
            private[mada] type is3 <: Boolean
            private[mada] type is4 <: Boolean
            private[mada] type is5 <: Boolean
            private[mada] type is6 <: Boolean
            private[mada] type is7 <: Boolean
            private[mada] type is8 <: Boolean
            private[mada] type is9 <: Boolean
            private[mada] type is10 <: Boolean
        }

        object Identity {

            sealed trait _0 extends Identity {
                override type increment = _1
                override type equals[that <: Identity] = that#is0

                private[mada] override type is0 = `true`
                private[mada] override type is1 = `false`
                private[mada] override type is2 = `false`
                private[mada] override type is3 = `false`
                private[mada] override type is4 = `false`
                private[mada] override type is5 = `false`
                private[mada] override type is6 = `false`
                private[mada] override type is7 = `false`
                private[mada] override type is8 = `false`
                private[mada] override type is9 = `false`
                private[mada] override type is10 = `false`
            }

            sealed trait _1 extends Identity {
                override type increment = _2
                override type equals[that <: Identity] = that#is1

                private[mada] override type is0 = `false`
                private[mada] override type is1 = `true`
                private[mada] override type is2 = `false`
                private[mada] override type is3 = `false`
                private[mada] override type is4 = `false`
                private[mada] override type is5 = `false`
                private[mada] override type is6 = `false`
                private[mada] override type is7 = `false`
                private[mada] override type is8 = `false`
                private[mada] override type is9 = `false`
                private[mada] override type is10 = `false`
            }

            sealed trait _2 extends Identity {
                override type increment = _3
                override type equals[that <: Identity] = that#is2

                private[mada] override type is0 = `false`
                private[mada] override type is1 = `false`
                private[mada] override type is2 = `true`
                private[mada] override type is3 = `false`
                private[mada] override type is4 = `false`
                private[mada] override type is5 = `false`
                private[mada] override type is6 = `false`
                private[mada] override type is7 = `false`
                private[mada] override type is8 = `false`
                private[mada] override type is9 = `false`
                private[mada] override type is10 = `false`
            }

            sealed trait _3 extends Identity {
                override type increment = _4
                override type equals[that <: Identity] = that#is3

                private[mada] override type is0 = `false`
                private[mada] override type is1 = `false`
                private[mada] override type is2 = `false`
                private[mada] override type is3 = `true`
                private[mada] override type is4 = `false`
                private[mada] override type is5 = `false`
                private[mada] override type is6 = `false`
                private[mada] override type is7 = `false`
                private[mada] override type is8 = `false`
                private[mada] override type is9 = `false`
                private[mada] override type is10 = `false`
            }

            sealed trait _4 extends Identity {
                override type increment = _5
                override type equals[that <: Identity] = that#is4

                private[mada] override type is0 = `false`
                private[mada] override type is1 = `false`
                private[mada] override type is2 = `false`
                private[mada] override type is3 = `false`
                private[mada] override type is4 = `true`
                private[mada] override type is5 = `false`
                private[mada] override type is6 = `false`
                private[mada] override type is7 = `false`
                private[mada] override type is8 = `false`
                private[mada] override type is9 = `false`
                private[mada] override type is10 = `false`
            }

            sealed trait _5 extends Identity {
                override type increment = _6
                override type equals[that <: Identity] = that#is5

                private[mada] override type is0 = `false`
                private[mada] override type is1 = `false`
                private[mada] override type is2 = `false`
                private[mada] override type is3 = `false`
                private[mada] override type is4 = `false`
                private[mada] override type is5 = `true`
                private[mada] override type is6 = `false`
                private[mada] override type is7 = `false`
                private[mada] override type is8 = `false`
                private[mada] override type is9 = `false`
                private[mada] override type is10 = `false`
            }

            sealed trait _6 extends Identity {
                override type increment = _7
                override type equals[that <: Identity] = that#is6

                private[mada] override type is0 = `false`
                private[mada] override type is1 = `false`
                private[mada] override type is2 = `false`
                private[mada] override type is3 = `false`
                private[mada] override type is4 = `false`
                private[mada] override type is5 = `false`
                private[mada] override type is6 = `true`
                private[mada] override type is7 = `false`
                private[mada] override type is8 = `false`
                private[mada] override type is9 = `false`
                private[mada] override type is10 = `false`
            }

            sealed trait _7 extends Identity {
                override type increment = _8
                override type equals[that <: Identity] = that#is7

                private[mada] override type is0 = `false`
                private[mada] override type is1 = `false`
                private[mada] override type is2 = `false`
                private[mada] override type is3 = `false`
                private[mada] override type is4 = `false`
                private[mada] override type is5 = `false`
                private[mada] override type is6 = `false`
                private[mada] override type is7 = `true`
                private[mada] override type is8 = `false`
                private[mada] override type is9 = `false`
                private[mada] override type is10 = `false`
             }

            sealed trait _8 extends Identity {
                override type increment = _9
                override type equals[that <: Identity] = that#is8

                private[mada] override type is0 = `false`
                private[mada] override type is1 = `false`
                private[mada] override type is2 = `false`
                private[mada] override type is3 = `false`
                private[mada] override type is4 = `false`
                private[mada] override type is5 = `false`
                private[mada] override type is6 = `false`
                private[mada] override type is7 = `false`
                private[mada] override type is8 = `true`
                private[mada] override type is9 = `false`
                private[mada] override type is10 = `false`
            }

            sealed trait _9 extends Identity {
                override type increment = _10
                override type equals[that <: Identity] = that#is9

                private[mada] override type is0 = `false`
                private[mada] override type is1 = `false`
                private[mada] override type is2 = `false`
                private[mada] override type is3 = `false`
                private[mada] override type is4 = `false`
                private[mada] override type is5 = `false`
                private[mada] override type is6 = `false`
                private[mada] override type is7 = `false`
                private[mada] override type is8 = `false`
                private[mada] override type is9 = `true`
                private[mada] override type is10 = `false`
            }

            sealed trait _10 extends Identity {
                override type increment = error
                override type equals[that <: Identity] = that#is10

                private[mada] override type is0 = `false`
                private[mada] override type is1 = `false`
                private[mada] override type is2 = `false`
                private[mada] override type is3 = `false`
                private[mada] override type is4 = `false`
                private[mada] override type is5 = `false`
                private[mada] override type is6 = `false`
                private[mada] override type is7 = `false`
                private[mada] override type is8 = `false`
                private[mada] override type is9 = `false`
                private[mada] override type is10 = `true`
            }

        }

    }

}
