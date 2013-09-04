import command.{Author, AppName, CommandGenerator}
import java.awt.Robot
import CommandGenerator._


object Automater  {


  def main(args: Array[String]) {

    implicit val app = AppName("com.jetbrains.intellij")
    implicit val author = Author("Nick")
    //http://stackoverflow.com/questions/294167/what-are-the-most-useful-intellij-idea-keyboard-shortcuts

    val commands = Seq(

      //tabs
      "Close Tab" >> "Shift-Command-F4",
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

      "Implementation" >> "Option-Shift-b",


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

    saveCommands(commands, "automator-dragon.commandstext")

  }

}