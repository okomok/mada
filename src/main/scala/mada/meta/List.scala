

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.meta


// See: HList.scala
//      at http://www.assembla.com/wiki/show/metascala


@provider
trait Lists { this: Meta.type =>


// List

    sealed trait List {
        type head
        type tail <: List

        type append[l <: List] <: List
        type at[i <: Int]
        type drop[i <: Int] <: List
        type take[i <: Int] <: List
        type length <: Int
    }

    sealed trait nil extends List {
        override type head = error
        override type tail = nil

        override type append[l <: List] = l
        override type at[i <: Int] = error
        override type drop[i <: Int] = nil
        override type take[i <: Int] = nil
        override type length = _0I
    }

    sealed trait cons[h, t <: List] extends List {
        type `this` = cons[h, t]

        override type head = h
        override type tail = t

        override type append[l <: List] = cons[h, t#append[l]]
        override type at[i <: Int] = atImpl[`this`, i#toNat] // dropImpl[`this`, i#toNat]#head
        override type drop[i <: Int] = dropImpl[`this`, i#toNat]
        override type take[i <: Int] = takeImpl[`this`, i#toNat]
        override type length = t#length#increment

    }

    type ::[h, t <: List] = cons[h, t]


// at

    type atImpl[l <: List, n <: Nat] = n#accept[atVisitor[l]]

    sealed trait atVisitor[l <: List] extends Nat.Visitor {
        override type Result = Any
        override type visitZero = l#head
        override type visitSucc[n <: Nat] = n#accept[atVisitor[l#tail]]
    }


// drop

    type dropImpl[l <: List, n <: Nat] = n#accept[dropVisitor[l]]

    sealed trait dropVisitor[l <: List] extends Nat.Visitor {
        override type Result = List
        override type visitZero = l
        override type visitSucc[n <: Nat] = n#accept[dropVisitor[l#tail]]
    }


// take

    type takeImpl[l <: List, n <: Nat] = n#accept[takeVisitor[l]]

    sealed trait takeVisitor[l <: List] extends Nat.Visitor {
        override type Result = List
        override type visitZero = nil
        override type visitSucc[n <: Nat] = cons[l#head, n#accept[takeVisitor[l#tail]]]
    }

}
