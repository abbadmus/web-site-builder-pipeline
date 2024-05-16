package staticSite

import scalatags.Text.all._
import org.commonmark.node._;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer

object App {
  def main(args: Array[String]): Unit = {

    val postInfo = os
      .list(os.pwd / "posts")
      .map { p =>
        val s"$prefix - $suffix.md" = p.last
        (prefix, suffix, p)
      }
      .sortBy(_._1.toInt)

    println(postInfo)

    println("POSTS")
    postInfo.foreach(println)

    os.remove.all(os.pwd / "result")
    os.makeDir.all(os.pwd / "result" / "post")

    def mdNameToHtml(name: String): String = name.replace(" ", "-").toLowerCase() + ".html"

    for ((_, suffix, path) <- postInfo){
      val parser: Parser = Parser.builder().build()
      val document: Node = parser.parse(os.read(path))
      val renderer: HtmlRenderer = HtmlRenderer.builder().build()
      val out = renderer.render(document)

      os.write(
        os.pwd / "result" / "post" / mdNameToHtml(suffix),
        doctype("html")(
          html(
            body(
              h1("Blog", " / ", suffix),
              raw(out)
            )
          )
        )
      )
    }

  //    os.write(
  //      os.pwd / "result" / "index.html",
  //      doctype("html")(
  //        html(
  //          body(
  //            h1("Blog"),
  //            for ((_, suffix, _) <- postInfo) yield h2(suffix)
  //          )
  //        )
  //      )
  //    )

    val parser2 = Parser.builder().build()

    val document2 = parser2.parse("This is *Sparta*")
    val renderer2 = HtmlRenderer.builder().build()
    val output2 = renderer2.render(document2)
    println(output2)

  }

}
