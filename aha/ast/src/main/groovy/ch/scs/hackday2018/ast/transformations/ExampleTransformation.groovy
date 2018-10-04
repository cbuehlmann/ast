package ch.scs.hackday2018.ast.transformations

import org.codehaus.groovy.ast.*
import org.codehaus.groovy.ast.stmt.Statement
import org.codehaus.groovy.control.*
import org.codehaus.groovy.transform.*

@GroovyASTTransformation(phase = CompilePhase.INSTRUCTION_SELECTION)
class ExampleTransformation extends AbstractASTTransformation {
  @Override
  void visit(ASTNode[] astNodes, SourceUnit source) {
    if (!astNodes) return
    if (!astNodes[0]) return
    if (!astNodes[1]) return
    if (!(astNodes[0] instanceof AnnotationNode)) return
    if (astNodes[0].classNode?.name != Marker.class.name) return

    MethodNode method = (MethodNode) astNodes[1]

    printMethod(method)
  }

  void printMethod(MethodNode method) {
    println("Method $method.name")
    Statement code = method.code
    println(code.text)

    def visitor = new CodeVisitor()
    code.visit(visitor)
  }
}
