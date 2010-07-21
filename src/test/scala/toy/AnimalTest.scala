

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.madatest; package toy; package animaltest


import com.github.okomok.mada

//import junit.framework.Assert._

class Food
abstract class Animal {
    type SuitableFood <: Food
    def eat(f: SuitableFood): Unit
}

class Grass extends Food
class Cow extends Animal {
    override type SuitableFood = Grass
    override def eat(f: Grass): Unit = ()
}

class Fish extends Food


abstract class AnimalG[SuitableFood <: Food] {
    def eat(f: SuitableFood): Unit
}

class CowG extends AnimalG[Grass] {
    override def eat(f: Grass): Unit = ()
}


class AnimalTezt {

    def bar(bessy: Animal): Unit = {
        // bessy.eat(new Grass) // type mismatch: Grass -> bessy.SuitableFood
        // bessy.eat(new bessy.SuitableFood) // class type required
        ()
    }

    def buz[SuitableFood <: Food](bessy: AnimalG[SuitableFood]): Unit = {
        ()
    }

    def bul(bessy: Cow): Unit = {
        bessy.eat(new bessy.SuitableFood)
        bessy.eat(new Grass)
        bessy.eat(new Cow#SuitableFood)
        ()
    }

}

