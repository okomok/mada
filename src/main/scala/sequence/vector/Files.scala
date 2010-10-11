

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package com.github.okomok.mada
package sequence; package vector


import java.io.{File, RandomAccessFile, Closeable}


// Char

private
case class CharFile(_1: RandomAccessFile) extends Auto[Vector[Char]] {
    override def open: Vector[Char] = new CharFileVector(_1)
    override def close = _1.close
}

private class CharFileVector(_1: RandomAccessFile) extends Vector[Char] {
    override def start = 0
    override def end = _1.length.toInt / 2
    override def apply(i: Int) = { _1.seek(i * 2); _1.readChar }
    override def update(i: Int, e: Char) = { _1.seek(i * 2); _1.writeChar(e) }
}


// Int

private
case class IntFile(_1: RandomAccessFile) extends Auto[Vector[Int]] {
    override def open: Vector[Int] = new IntFileVector(_1)
    override def close = _1.close
}

private class IntFileVector(_1: RandomAccessFile) extends Vector[Int] {
    override def start = 0
    override def end = _1.length.toInt / 4
    override def apply(i: Int) = { _1.seek(i * 4); _1.readInt }
    override def update(i: Int, e: Int) = { _1.seek(i * 4); _1.writeInt(e) }
}


// Long

private
case class LongFile(_1: RandomAccessFile) extends Auto[Vector[Long]] {
    override def open: Vector[Long] = new LongFileVector(_1)
    override def close = _1.close
}

private class LongFileVector(_1: RandomAccessFile) extends Vector[Long] {
    override def start = 0
    override def end = _1.length.toInt / 8
    override def apply(i: Int) = { _1.seek(i * 8); _1.readLong }
    override def update(i: Int, e: Long) = { _1.seek(i * 8); _1.writeLong(e) }
}
