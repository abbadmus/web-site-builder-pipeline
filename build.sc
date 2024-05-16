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
