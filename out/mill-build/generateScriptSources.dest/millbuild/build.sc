package millbuild

import _root_.mill.runner.MillBuildRootModule

object MiscInfo_build {
  implicit lazy val millBuildRootModuleInfo: _root_.mill.runner.MillBuildRootModule.Info = _root_.mill.runner.MillBuildRootModule.Info(
    Vector("/Users/abbadmus/Desktop/hands-on-scala-programming/static site/out/mill-launcher/0.11.7-29-f2e220.jar").map(_root_.os.Path(_)),
    _root_.os.Path("/Users/abbadmus/Desktop/hands-on-scala-programming/static site"),
    _root_.os.Path("/Users/abbadmus/Desktop/hands-on-scala-programming/static site"),
  )
  implicit lazy val millBaseModuleInfo: _root_.mill.main.RootModule.Info = _root_.mill.main.RootModule.Info(
    millBuildRootModuleInfo.projectRoot,
    _root_.mill.define.Discover[build]
  )
}
import MiscInfo_build.{millBuildRootModuleInfo, millBaseModuleInfo}
object build extends build
class build extends _root_.mill.main.RootModule {

//MILL_ORIGINAL_FILE_PATH=/Users/abbadmus/Desktop/hands-on-scala-programming/static site/build.sc
//MILL_USER_CODE_START_MARKER
import mill._, scalalib._

object staticSite extends ScalaModule {

  def scalaVersion = "2.13.11"

  def ivyDeps: T[Agg[Dep]] = super.ivyDeps() ++ Agg(
    ivy"com.lihaoyi::scalatags:0.12.0",
    ivy"com.lihaoyi::mainargs:0.6.2",
    ivy"com.lihaoyi::scalatags:0.11.0",
    ivy"com.lihaoyi::os-lib::0.9.0",
    ivy"org.commonmark:commonmark:0.22.0"
  )

  object test extends ScalaTests {
    def ivyDeps: T[Agg[Dep]] = Agg(ivy"com.lihaoyi::utest:0.7.11")
    def testFramework = "utest.runner.Framework"
  }
}

}