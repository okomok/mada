

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


// See: http://www.assembla.com/wiki/show/metascala



// will be replaced by Meta.IntList.
@provider
trait Nats { this: Meta.type =>

    sealed trait Nat extends Operatable {
        type toInt <: Int
    }

    sealed trait _0N extends Nat {
        override type toInt = _0I
    }

    sealed trait Succ[n <: Nat] extends Nat {
        override type toInt = n#toInt#increment
    }

    type _1N = Succ[_0N]
    type _2N = Succ[_1N]
    type _3N = Succ[_2N]
    type _4N = Succ[_3N]
    type _5N = Succ[_4N]
    type _6N = Succ[_5N]
    type _7N = Succ[_6N]
    type _8N = Succ[_7N]
    type _9N = Succ[_8N]
    type _10N = Succ[_9N]

//  implicit val _0ToInt = TypeToValue[_0, Int](0)
//  implicit def succToInt[P <: Nat](implicit v : TypeToValue[P, Int]) = TypeToValue[Succ[P], Int](1 + v.value)
}
