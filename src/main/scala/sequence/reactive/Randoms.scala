

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive; package generator


import java.util.Random


object Randoms {

    class OfBoolean(val random: Random) extends Generator.Trivial[Boolean] {
        override protected def generateTo(f: Boolean => Unit) = f(random.nextBoolean)
    }

    class OfDouble(val random: Random) extends Generator.Trivial[Double] {
        override protected def generateTo(f: Double => Unit) = f(random.nextDouble)
    }

    class OfFloat(val random: Random) extends Generator.Trivial[Float] {
        override protected def generateTo(f: Float => Unit) = f(random.nextFloat)
    }

    class OfGaussian(val random: Random) extends Generator.Trivial[Double] {
        override protected def generateTo(f: Double => Unit) = f(random.nextGaussian)
    }

    class OfInt(val random: Random) extends Generator.Trivial[Int] {
        override protected def generateTo(f: Int => Unit) = f(random.nextInt)
    }

    class OfLong(val random: Random) extends Generator.Trivial[Long] {
        override protected def generateTo(f: Long => Unit) = f(random.nextLong)
    }

    class OfBytes(val random: Random, val length: Int) extends Generator.Trivial[Array[Byte]] {
        override protected def generateTo(f: Array[Byte] => Unit) = {
            val arr = new Array[Byte](length)
            random.nextBytes(arr)
            f(arr)
        }
    }

}
