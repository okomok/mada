

// Copyright Shunsuke Sogame 2008-2010.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package reactive


import java.util.Random


object Randoms {

    class OfBoolean(val random: Random) extends GeneratorOnce[Boolean] {
        override def generateOne = out(random.nextBoolean)
    }

    class OfDouble(val random: Random) extends GeneratorOnce[Double] {
        override def generateOne = out(random.nextDouble)
    }

    class OfFloat(val random: Random) extends GeneratorOnce[Float] {
        override def generateOne = out(random.nextFloat)
    }

    class OfGaussian(val random: Random) extends GeneratorOnce[Double] {
        override def generateOne = out(random.nextGaussian)
    }

    class OfInt(val random: Random) extends GeneratorOnce[Int] {
        override def generateOne = out(random.nextInt)
    }

    class OfLong(val random: Random) extends GeneratorOnce[Long] {
        override def generateOne = out(random.nextLong)
    }

    class OfBytes(val random: Random, val length: Int) extends GeneratorOnce[Array[Byte]] {
        override def generateOne = {
            val arr = new Array[Byte](length)
            random.nextBytes(arr)
            out(arr)
        }
    }

}
