package ch.scs.hackday2018.ast

class Main {

  static void main(String[] args) {
    File sourceFile = new File('src/main/groovy/ch/scs/hackday2018/ast/Test.groovy')
    GroovyShell shell = new GroovyShell()
    Script script = shell.parse(sourceFile)
    script.run()
  }
}