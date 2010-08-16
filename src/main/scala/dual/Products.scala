

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package dual


import nat.peano


// Product

trait Product extends Any {
    type self <: Product

    final override  def asProduct = self
    final override type asProduct = self

     def arity: arity
    type arity <: Nat

     def productElement[n <: Nat](n: n): productElement[n]
    type productElement[n <: Nat] <: Any

    override def canEqual(that: scala.Any) = that.isInstanceOf[Product]
}


// Product1

trait Product1 extends Product {
    type self <: Product1

    final override  def asProduct1 = self
    final override type asProduct1 = self

     def _1: _1
    type _1 <: Any

    override def canEqual(that: scala.Any) = that.isInstanceOf[Product1]
}

private[dual]
trait AbstractProduct1 extends Product1 {
    final override  def arity: arity = peano._1
    final override type arity        = peano._1

    final override  def productElement[n <: Nat](n: n): productElement[n] =
        `if`(n.equal(peano._0),
            const0(_1),
            throw0(new IndexOutOfBoundsException(n.toString))
        ).apply

    final override type productElement[n <: Nat] =
        `if`[n#equal[peano._0],
            const0[_1],
            throw0[_]
        ]#apply

    final override  def asList: asList = _1 :: Nil
    final override type asList         = _1 :: Nil

    final override  def naturalOrdering: naturalOrdering = list.naturalOrdering
    final override type naturalOrdering                  = list.naturalOrdering
}


// Product2

trait Product2 extends Product {
    type self <: Product2

    final override  def asProduct2 = self
    final override type asProduct2 = self

     def _1: _1
    type _1 <: Any

     def _2: _2
    type _2 <: Any

    override def canEqual(that: scala.Any) = that.isInstanceOf[Product2]
}

private[dual]
trait AbstractProduct2 extends Product2 {
    final override  def arity: arity = peano._2
    final override type arity        = peano._2

    final override  def productElement[n <: Nat](n: n): productElement[n] =
        `if`(n.equal(peano._0),
            const0(_1),
            `if`(n.equal(peano._1),
                const0(_2),
                throw0(new IndexOutOfBoundsException(n.toString))
            )
        ).apply.asInstanceOf[productElement[n]]

    final override type productElement[n <: Nat] =
        `if`[n#equal[peano._0],
            const0[_1],
            `if`[n#equal[peano._1],
                const0[_2],
                throw0[_]
            ]
        ]#apply

    final override  def asList: asList = _1 :: _2 :: Nil
    final override type asList         = _1 :: _2 :: Nil

    final override  def naturalOrdering: naturalOrdering = list.naturalOrdering
    final override type naturalOrdering                  = list.naturalOrdering
}


// Product3

trait Product3 extends Product {
    type self <: Product3

    final override  def asProduct3 = self
    final override type asProduct3 = self

     def _1: _1
    type _1 <: Any

     def _2: _2
    type _2 <: Any

     def _3: _3
    type _3 <: Any

    override def canEqual(that: scala.Any) = that.isInstanceOf[Product3]
}

private[dual]
trait AbstractProduct3 extends Product3 {
    final override  def arity: arity = peano._3
    final override type arity        = peano._3

    final override  def productElement[n <: Nat](n: n): productElement[n] =
        `if`(n.equal(peano._0),
            const0(_1),
            `if`(n.equal(peano._1),
                const0(_2),
                `if`(n.equal(peano._2),
                    const0(_3),
                    throw0(new IndexOutOfBoundsException(n.toString))
                )
            )
        ).apply.asInstanceOf[productElement[n]]

    final override type productElement[n <: Nat] =
        `if`[n#equal[peano._0],
            const0[_1],
            `if`[n#equal[peano._1],
                const0[_2],
                `if`[n#equal[peano._2],
                    const0[_3],
                    throw0[_]
                ]
            ]
        ]#apply

    final override  def asList: asList = _1 :: _2 :: _3 :: Nil
    final override type asList         = _1 :: _2 :: _3 :: Nil

    final override  def naturalOrdering: naturalOrdering = list.naturalOrdering
    final override type naturalOrdering                  = list.naturalOrdering
}
