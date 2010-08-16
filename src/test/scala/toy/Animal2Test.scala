

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package toy; package animal2test


import com.github.okomok.mada

//import junit.framework.Assert._

class Food
abstract class Animal {
    type Self <: Animal
    type SuitableFood <: Food
    def eat(f: SuitableFood): Unit = ()
    def iam(s: Self): Unit = ()
}

class Grass extends Food
class Cow extends Animal {
    override type Self = Cow
    override type SuitableFood = Grass
}

class Fish extends Food


class Animal2Tezt {

    def foo(bessy: Cow): Unit = {
//        bessy.eat(12) // required bessy.SuitableFood
        bessy.eat(new bessy.SuitableFood)
        bessy.eat(new Grass)
        bessy.eat(new Cow#SuitableFood)
        ()
    }

    def buz(bessy: Cow, bessy2: Cow): Unit = {
        mada.dual.free.assertSame[bessy.Self, Cow]
//        bessy.iam(12) // required bessy.Self
        bessy.iam(bessy2)
        bessy.iam(bessy)
        ()
    }

    def bar(bessy: Animal): Unit = {
//        bessy.iam(12) // required bessy.Self
//        bessy.iam(bessy) // required bessy.Self
        ()
    }

    /* illegal dependent method type (without -Xexperimental)
    def fuz(bessy: Animal, self: bessy.Self): Unit = {
        bessy.iam(self) // required bessy.Self
        ()
    }
    */
}

