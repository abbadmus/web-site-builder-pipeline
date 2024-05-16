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

    def mdNameToHtml(name: String): String =
      name.replace(" ", "-").toLowerCase() + ".html"

    val bootstrapCss = link(
      rel := "stylesheet",
      href := "https://cdn.jsdelivr.net/npm/bootstrap@4.4.1/dist/css/bootstrap.min.css"
    )

    for ((_, suffix, path) <- postInfo) {
      val parser: Parser = Parser.builder().build()
      val document: Node = parser.parse(os.read(path))
      val renderer: HtmlRenderer = HtmlRenderer.builder().build()
      val out = renderer.render(document)

      os.write(
        os.pwd / "result" / "post" / mdNameToHtml(suffix),
        doctype("html")(
          html(
            head(bootstrapCss),
            body(
              h1(a(href := "../index.html")("Blog"), " / ", suffix),
              raw(out)
            )
          )
        )
      )
    }

    os.write(
      os.pwd / "result" / "index.html",
      doctype("html")(
        html(
          head(bootstrapCss),
          body(
            h1("Blog"),
            for ((_, suffix, _) <- postInfo)
              yield h2(a(href := ("post/" + mdNameToHtml(suffix)), suffix ))
          )
        )
      )
    )

    val parser2 = Parser.builder().build()

    val document2 = parser2.parse("This is *Sparta*")
    val renderer2 = HtmlRenderer.builder().build()
    val output2 = renderer2.render(document2)
    println(output2)

  }

}
