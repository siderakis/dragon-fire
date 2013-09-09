import command.{Command, Author, AppName, CommandGenerator}
import CommandGenerator._
import scala.Predef._


object DragonFire {

  implicit val author = Author("Nick")

  def main(args: Array[String]) {
    saveCommands(idea, "idea-dragon.commandstext")

  }


  private def idea: Seq[Command] = {
    //http://stackoverflow.com/questions/294167/what-are-the-most-useful-intellij-idea-keyboard-shortcuts
    implicit val app = AppName("com.jetbrains.intellij")

    val simpleCommands: Seq[Command] = Seq(
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

      "Camel" >!> "Terminal",
      "Escape" >> "Escape"
    )





    val expandedCommands =
      Seq("Up", "Down", "Left", "Right").flatMap(d => expander(d, d + "Arrow"))
    simpleCommands ++ expandedCommands
  }
}