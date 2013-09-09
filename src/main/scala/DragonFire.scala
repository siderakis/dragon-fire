import command.{Command, Author, AppName, CommandGenerator}
import CommandGenerator._
import scala.Predef._


object DragonFire {

  implicit val author = Author("Nick")

  def main(args: Array[String]) {
    saveCommands(idea, "export/idea-dragon.commandstext")
    saveCommands(terminal, "export/terminal-dragon.commandstext")
  }

  private def terminal: Seq[Command] = {
    implicit val app = AppName("com.googlecode.iterm2", 1)
    Seq(
      //These are commands for the terminal
      "CD" >> "c d",
      "LS" >> "l s",
      "SBT" >> "s b t",
      "Enter" >> "Return"
    )
  }

  private def idea: Seq[Command] = {
    //http://stackoverflow.com/questions/294167/what-are-the-most-useful-intellij-idea-keyboard-shortcuts
    implicit val app = AppName("com.jetbrains.intellij", 12)
    def expander(word: String, action: String): Seq[Command] =
      (1 to 19)
        .map(i => s"$word ${words(i)} times" >> ((action + " ") * i).trim)

    Seq(
      "Duplicate Line" >> "Command-d",
      "Go To Line" >> "Command-g",
      //tabs
      "Close Tab" >> "Shift-Command-F4",
      "Toggle Tab" >> "Control-Tab",

      //      "Tab" >> "Control-Tab",
      //      "P Tab" >> "Control",

      "Dot" >> ".",

      //moving cursor
      "Top" >> "Command-Home",
      "Start" >> "Command-LeftArrow",
      "End" >> "Command-RightArrow",

      "Up" >> "UpArrow",


      "Down" >> "DownArrow",

      //selecting
      "Select to end" >> "Command-Shift-RightArrow",
      "Select to start" >> "Command-Shift-LeftArrow ",
      "Sword" >> "Command-w", //? "Select Word"


      //
      "Open Class" >> "Command-n",
      "Open File" >> "Shift-Command-n",
      "New Class" >> "Control-n",

      "Implementation" >> "Option-Shift-b",
      "File Structure" >> "Control-Tab-7", //Command-F12",
      "Type Hierarchy" >> "Control-h",

      //debugging

      //Navigation
      "Go to Declaration" >> "Command-b",

      //refactoring
      "Format" >> "Option-Command-l",
      "rename" >> "Shift-F6",

      //deleting
      "Delete Word" >> "Command-w Delete",
      "Delete Line" >> "Command-y",
      "Delete" >> "Delete",

      "Complete" >> "Control-Space",

      "Comment line" >> "Command-/",
      "Uncomment line" >> "Command-/",


      "Show shortcuts" >> "Command-Shift-a",

      "Termie" >! "Terminal",

      "Camel case" >** "camel",

      "Escape" >> "Escape"
    ) ++
      Seq("Up", "Down", "Left", "Right").flatMap(d => expander(d, d + "Arrow"))
  }
}