

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package iterative


import java.util.Random


object Randoms {

    case class OfBoolean(random: Random) extends Forwarder[Boolean] {
        override protected val delegate = iterate(random.nextBoolean){_ => random.nextBoolean}
    }

    case class OfDouble(random: Random) extends Forwarder[Double] {
        override protected val delegate = iterate(random.nextDouble){_ => random.nextDouble}
    }

    case class OfFloat(random: Random) extends Forwarder[Float] {
        override protected val delegate = iterate(random.nextFloat){_ => random.nextFloat}
    }

    case class OfGaussian(random: Random) extends Forwarder[Double] {
        override protected val delegate = iterate(random.nextGaussian){_ => random.nextGaussian}
    }

    case class OfInt(random: Random) extends Forwarder[Int] {
        override protected val delegate = iterate(random.nextInt){_ => random.nextInt}
    }

    case class OfLong(random: Random) extends Forwarder[Long] {
        override protected val delegate = iterate(random.nextLong){_ => random.nextLong}
    }

    case class OfBytes(random: Random, length: Int) extends Forwarder[Array[Byte]] {
        private[this] val a = new Array[Byte](length)
        private def _nextBytes = { random.nextBytes(a); a }
        override protected val delegate = iterate(_nextBytes){_ => _nextBytes}
    }

}
