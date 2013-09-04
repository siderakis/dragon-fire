package command

import scala.xml.NodeSeq
import scala.util.Random

object Template {
 val vendor = "Nick"

  //    <!DOCTYPE database SYSTEM "file:///System/Library/DTDs/CoreData.dtd">
  //    <?xml version="1.0" standalone="no"?>
  def template(body: NodeSeq) =
      <database>
        <databaseInfo>
          <version>134481920</version>
          <UUID>C6588137-5AC4-4FA0-ACA4-818FBA19D3AB</UUID>
          <nextObjectID>113</nextObjectID>
          <metadata>
            <plist version="1.0">
              <dict>
                <key>NSPersistenceFrameworkVersion</key>
                <integer>407</integer>
                <key>NSStoreModelVersionHashes</key>
                <dict>
                  <key>action</key>
                  <data>Gl79yicU/qMmmjW+02T6r/N/3wY/MXt1/ETG6BgiQvk=</data>
                  <key>command</key>
                  <data>LnTEAxQizumf4LEx7vWu/AEuw8pYLvlU+A+QAxxNV1Q=</data>
                  <key>location</key>
                  <data>l1GW8zsQs6xToCTE303HdInkm0pvem69Qmej6Ixq3k4=</data>
                  <key>trigger</key>
                  <data>kWwewq0GT8KPB4ELML1wT0S2IYIZ5+/6CI0GsK9LDns=</data>
                </dict>
                <key>NSStoreModelVersionHashesVersion</key>
                <integer>3</integer>
                <key>NSStoreModelVersionIdentifiers</key>
                <array>
                  <string></string>
                </array>
              </dict>
            </plist>
          </metadata>
        </databaseInfo>
        {body}
      </database>
  ;


  val command = (`type`:String) => (app:AppName) => (s:Scope)  =>
  <object type="COMMAND" id={s.commandId}>
    <attribute name="version" type="int32">1</attribute>
    <attribute name="vendor" type="string">{vendor}</attribute>
    <attribute name="type" type="string">{`type`}</attribute>
    <attribute name="spokenlanguage" type="string">en_US</attribute>
    <attribute name="oslanguage" type="string">en_GB</attribute>
    <attribute name="isspelling" type="bool">0</attribute>
    <attribute name="issleep" type="bool">0</attribute>
    <attribute name="isdictation" type="bool">0</attribute>
    <attribute name="iscorrection" type="bool">0</attribute>
    <attribute name="iscommand" type="bool">1</attribute>
    <attribute name="engineid" type="int32">-1</attribute>
    <attribute name="display" type="bool">1</attribute>
    <attribute name="commandid" type="int32">{Random.nextInt(1E9.toInt)}</attribute>
    <attribute name="appversion" type="int32">12</attribute>
    <attribute name="appbundle" type="string">{app.s}</attribute>
    <attribute name="active" type="bool">1</attribute>
    <relationship name="currentaction" type="1/1" destination="ACTION"></relationship>
    <relationship name="currenttrigger" type="1/1" destination="TRIGGER"></relationship>
    <relationship name="location" type="1/1" destination="LOCATION"></relationship>
    <relationship name="action" type="0/0" destination="ACTION" idrefs={s.actionId}></relationship>
    <relationship name="trigger" type="1/0" destination="TRIGGER" idrefs={s.triggerId}></relationship>
  </object>

  val trigger = (name:String,desc:String) => ( s:Scope) =>
  <object type="TRIGGER" id={s.triggerId}>
    <attribute name="string" type="string">{name}</attribute>
    <attribute name="spokenlanguage" type="string">en_US</attribute>
    <attribute name="isuser" type="bool">1</attribute>
    <attribute name="desc" type="string">{desc}</attribute>
    <relationship name="command" type="1/1" destination="COMMAND" idrefs={s.commandId}></relationship>
    <relationship name="currentcommand" type="1/1" destination="COMMAND"></relationship>
  </object>

  val action = (action:String)=> (s:Scope)   =>
  <object type="ACTION" id={s.actionId}>
    <attribute name="text" type="string">{action}</attribute>
    <attribute name="oslanguage" type="string">en_GB</attribute>
    <attribute name="isuser" type="bool">1</attribute>
    <relationship name="command" type="1/1" destination="COMMAND" idrefs={s.commandId}></relationship>
    <relationship name="currentcommand" type="1/1" destination="COMMAND"></relationship>
  </object>


}
