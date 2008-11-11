
package mada.rng


import java.io.{File, RandomAccessFile}


object FromIntFile {
    def apply(a: RandomAccessFile): Rng[Int] = new IntFileRng(a)
}

class IntFileRng(a: RandomAccessFile) extends IndexAccessRng[Int] {
    override def _set(i: Long, e: Int) = { a.seek(i * 4); a.write(e) }
    override def _get(i: Long) = { a.seek(i); a.read }
    override def _size = a.length / 4
}


/*
object InFile {
    def apply[X](file: File, mode: String, op: Rng[Byte] => X): X = {
        val a = new RandomAccessFile(file, mode)
        val ia = new IndexAccess[Byte] {
            override def _set(i: Long, e: Byte) = { a.seek(i); a.writeByte(e) }
            override def _get(i: Long) = { a.seek(i); a.readByte }
            override def _size = a.length
        }
        val x = op(new IndexAccessRng(ia))
        a.close
        x
    }
}
*/
