

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


// See: Nats.scala
//      at http://www.assembla.com/wiki/show/metascala


@provider
trait Nats { this: Meta.type =>

    object Nat {

        sealed trait zero extends Nat {
            override type toInt = _0I
            override type accept[v <: Visitor] = v#visitZero
        }

        sealed trait succ[n <: Nat] extends Nat {
            override type toInt = n#toInt#increment
            override type accept[v <: Visitor] = v#visitSucc[n]
        }

        trait Visitor {
            type Result
            type visitZero <: Result
            type visitSucc[n <: Nat] <: Result
        }

    }

    trait Nat {
        type toInt <: Int
        type accept[v <: Nat.Visitor] <: v#Result // More generic algorithms (fold etc) won't work.
    }

}
