

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


import java.io.{ File, RandomAccessFile }


// Char

class CharFileVector private (val randomAccessFile: RandomAccessFile) extends Vector[Char] {
    def this(file: File, mode: String) = this(new RandomAccessFile(file, mode))
    def this(name: String, mode: String) = this(new RandomAccessFile(name, mode))
    def close = randomAccessFile.close

    override def size = randomAccessFile.length / 2
    override def apply(i: Long) = { randomAccessFile.seek(i * 2); randomAccessFile.readChar }
    override def update(i: Long, e: Char) = { randomAccessFile.seek(i * 2); randomAccessFile.writeChar(e) }
}


// Int

class IntFileVector private (val randomAccessFile: RandomAccessFile) extends Vector[Int] {
    def this(file: File, mode: String) = this(new RandomAccessFile(file, mode))
    def this(name: String, mode: String) = this(new RandomAccessFile(name, mode))
    def close = randomAccessFile.close

    override def size = randomAccessFile.length / 4
    override def apply(i: Long) = { randomAccessFile.seek(i * 4); randomAccessFile.readInt }
    override def update(i: Long, e: Int) = { randomAccessFile.seek(i * 4); randomAccessFile.writeInt(e) }
}


// Long

class LongFileVector private (val randomAccessFile: RandomAccessFile) extends Vector[Long] {
    def this(file: File, mode: String) = this(new RandomAccessFile(file, mode))
    def this(name: String, mode: String) = this(new RandomAccessFile(name, mode))
    def close = randomAccessFile.close

    override def size = randomAccessFile.length / 8
    override def apply(i: Long) = { randomAccessFile.seek(i * 8); randomAccessFile.readLong }
    override def update(i: Long, e: Long) = { randomAccessFile.seek(i * 8); randomAccessFile.writeLong(e) }
}
