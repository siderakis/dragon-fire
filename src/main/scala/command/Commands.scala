package command

case class Scope(actionId: String, triggerId: String, commandId: String)

object Scope {

  def apply(i: Int): Scope = {
    implicit def ii(i: Int) = "z" + i.toString
    this.apply(i + 1, i + 2, i + 3)
  }
}

case class Command(word: String, `type`: String, keys: String, description: String, app: AppName) {

  def toXml(i: Int) = {
    import Template._
    val s = Scope(i)
    command(`type`)(app)(s) ++ trigger(word, description)(s) ++ action(keys)(s)

  }

}