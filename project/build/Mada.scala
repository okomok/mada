


import sbt._


class Mada(info: ProjectInfo) extends DefaultProject(info) {
    //val junit = "junit" % "junit" % "3.8.2" % "test"
    override def compileOptions = super.compileOptions ++ compileOptions("-Yrecursion", "50")
    val junitInterface = "com.novocode" % "junit-interface" % "0.3" % "test"
    override def testFrameworks = super.testFrameworks ++ List(new TestFramework("com.novocode.junit.JUnitFrameworkNoMarker"))
}