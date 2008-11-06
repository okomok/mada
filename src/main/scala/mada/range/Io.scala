
package mada.range


import java.io.{File, RandomAccessFile}


object FromByteFile {
    def apply(a: RandomAccessFile): Range[Byte] = {
        val ia = new IndexAccess[Byte] {
            override def _set(i: Long, e: Byte) = { a.seek(i); a.writeByte(e) }
            override def _get(i: Long) = { a.seek(i); a.readByte }
            override def _size = a.length
        }
        new IndexAccessRange(ia) {
        }
    }
}

object FromCharFile {
    def apply(a: RandomAccessFile): Range[Char] = {
        val ia = new IndexAccess[Char] {
            override def _set(i: Long, e: Char) = { a.seek(i * 2); a.writeChar(e) }
            override def _get(i: Long) = { a.seek(i); a.readChar }
            override def _size = a.length / 2
        }
        new IndexAccessRange(ia) {
        }
    }
}

object FromIntFile {
    def apply(a: RandomAccessFile): Range[Int] = {
        val ia = new IndexAccess[Int] {
            override def _set(i: Long, e: Int) = { a.seek(i * 4); a.write(e) }
            override def _get(i: Long) = { a.seek(i); a.read }
            override def _size = a.length / 4
        }
        new IndexAccessRange(ia) {
        }
    }
}




/*
object InFile {
    def apply[X](file: File, mode: String, op: Range[Byte] => X): X = {
        val a = new RandomAccessFile(file, mode)
        val ia = new IndexAccess[Byte] {
            override def _set(i: Long, e: Byte) = { a.seek(i); a.writeByte(e) }
            override def _get(i: Long) = { a.seek(i); a.readByte }
            override def _size = a.length
        }
        val x = op(new IndexAccessRange(ia))
        a.close
        x
    }
}
*/
