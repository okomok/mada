

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


// See: Nats.scala
//      at http://www.assembla.com/wiki/show/metascala


@provider
trait Nats { this: Meta.type =>

    object Nat {

        sealed trait zero extends Nat {
            override type accept[v <: Visitor] = v#end
        }

        sealed trait succ[n <: Nat] extends Nat {
            override type accept[v <: Visitor] = v#visit[n]
        }

        trait Visitor {
            type Result
            type end <: Result
            type visit[n <: Nat] <: Result
        }

    }

    trait Nat {
        type accept[v <: Nat.Visitor] <: v#Result
    }

}
