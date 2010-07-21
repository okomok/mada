


import sbt._


class Mada(info: ProjectInfo) extends DefaultProject(info) {
    val junit = "junit" % "junit" % "3.8.2" % "test"
    override def compileOptions = super.compileOptions ++ compileOptions("-Yrecursion", "50")
}