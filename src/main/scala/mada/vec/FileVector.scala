

// Copyright Shunsuke Sogame 2008-2009.
// Distributed under the terms of an MIT-style license.


package mada.vec


import java.io.{ RandomAccessFile, File }


// Int

class IntFileVector private (f: RandomAccessFile) extends Vector[Int] {
    def this(file: File, mode: String) = this(new RandomAccessFile(file, mode))
    def this(name: String, mode: String) = this(new RandomAccessFile(name, mode))
    def close = f.close

    override def size = f.length / 4
    override def apply(i: Long) = { f.seek(i * 4); f.readInt }
    override def update(i: Long, e: Int) = { f.seek(i * 4); f.writeInt(e) }
}


// Long

class LongFileVector private (f: RandomAccessFile) extends Vector[Long] {
    def this(file: File, mode: String) = this(new RandomAccessFile(file, mode))
    def this(name: String, mode: String) = this(new RandomAccessFile(name, mode))
    def close = f.close

    override def size = f.length / 8
    override def apply(i: Long) = { f.seek(i * 8); f.readLong }
    override def update(i: Long, e: Long) = { f.seek(i * 8); f.writeLong(e) }
}
