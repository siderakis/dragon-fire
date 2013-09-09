package command

import java.io.File


case class CommandGenerator(word: String) {
  /*
  Alternate style:

  In "Intellij" {
    saying "Format" should "Format source code" byPressing "Command Shift N"
  }*/
  def >>(keys: String)(implicit app: AppName) = Command(word, "Keystroke", keys, s"""saying "$word" presses "$keys".""", app)

  def >!(keys: String)(implicit app: AppName) = Command(word, "Application", keys, s"""saying "$word" opens the application "$keys".""", app)

  def >*(keys: String)(implicit app: AppName) = Command(word, "ShellScript", keys, s"""saying "$word" runs a script.""", app)

  def >**(keys: String)(implicit app: AppName) = >*(CommandGenerator.dynamic(keys))

  //  def >:>(keys: String)(implicit app: AppName) = Command(word,"", keys, s"saying $word presses $keys", app)
}

//case class TriggerGenerator(trigger:String){
//
//  def ? (desc: String) =
//}

case class AppName(val s: String, val v: Int)

case class Author(val s: String) extends AnyVal

object CommandGenerator {


  val dynamic = (signal: String) =>
    s"""echo '$signal' | nc localhost 1201
    |osascript -e 'tell application "Terminal" to activate'""".stripMargin


  implicit def text2Command(word: String) = CommandGenerator(word)

  val words = Seq(
    "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
    "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
  )


  def saveCommands(commands: Seq[Command], fileName: String) = {
    val data = commands.zipWithIndex.map {
      case (a, b) => a.toXml(b * 3)
    }

    val file = Template.template(data.reduce(_ ++ _))

    printToFile(new File(fileName))(p => {
      file.foreach(p.println)
    })

    def printToFile(f: java.io.File)(op: java.io.PrintWriter => Unit) {
      val p = new java.io.PrintWriter(f)
      try {
        op(p)
      } finally {
        p.close()
      }
    }
  }


}



