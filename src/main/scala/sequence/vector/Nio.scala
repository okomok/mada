

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada; package sequence; package vector


private[mada] case class FromJByteBuffer(_1: java.nio.ByteBuffer) extends Vector[Byte] {
    override def start = 0
    override def end = _1.limit
    override def apply(i: Int) = _1.get(i)
    override def update(i: Int, e: Byte) = _1.put(i, e)
}

@visibleForTesting
case class FromJCharBuffer(_1: java.nio.CharBuffer) extends Vector[Char] {
    override def start = 0
    override def end = _1.limit
    override def apply(i: Int) = _1.get(i)
    override def update(i: Int, e: Char) = _1.put(i, e)
}

private[mada] case class FromJDoubleBuffer(_1: java.nio.DoubleBuffer) extends Vector[Double] {
    override def start = 0
    override def end = _1.limit
    override def apply(i: Int) = _1.get(i)
    override def update(i: Int, e: Double) = _1.put(i, e)
}

private[mada] case class FromJFloatBuffer(_1: java.nio.FloatBuffer) extends Vector[Float] {
    override def start = 0
    override def end = _1.limit
    override def apply(i: Int) = _1.get(i)
    override def update(i: Int, e: Float) = _1.put(i, e)
}

private[mada] case class FromJIntBuffer(_1: java.nio.IntBuffer) extends Vector[Int] {
    override def start = 0
    override def end = _1.limit
    override def apply(i: Int) = _1.get(i)
    override def update(i: Int, e: Int) = _1.put(i, e)
}

private[mada] case class FromJLongBuffer(_1: java.nio.LongBuffer) extends Vector[Long] {
    override def start = 0
    override def end = _1.limit
    override def apply(i: Int) = _1.get(i)
    override def update(i: Int, e: Long) = _1.put(i, e)
}

private[mada] case class FromJShortBuffer(_1: java.nio.ShortBuffer) extends Vector[Short] {
    override def start = 0
    override def end = _1.limit
    override def apply(i: Int) = _1.get(i)
    override def update(i: Int, e: Short) = _1.put(i, e)
}
