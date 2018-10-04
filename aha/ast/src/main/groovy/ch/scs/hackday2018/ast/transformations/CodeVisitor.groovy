package ch.scs.hackday2018.ast.transformations

import org.codehaus.groovy.ast.CodeVisitorSupport
import org.codehaus.groovy.ast.expr.*
import org.codehaus.groovy.ast.stmt.*

class CodeVisitor extends CodeVisitorSupport {

  int intentation = 0;

  void log(String message) {
    for (int i = 0; i < intentation; i++) {
      print('  ')
    }
    println(message)
  }

  void shift(Closure closure) {
    intentation++;
    closure.run()
    intentation--;
  }

  @Override
  void visitBlockStatement(BlockStatement statement) {
    log("Block start")
    shift {
      super.visitBlockStatement(statement)
    }
    log("Block end")
  }

  @Override
  void visitExpressionStatement(ExpressionStatement statement) {
    log("Expression: ${statement.expression.getClass().simpleName}")
    shift {
      super.visitExpressionStatement(statement)
    }
  }

  @Override
  void visitDeclarationExpression(DeclarationExpression expression) {
    log("declare")
    shift {
      super.visitDeclarationExpression(expression)
    }
  }

  @Override
  void visitMethodCallExpression(MethodCallExpression call) {
    log("Call $call.methodAsString")
    shift {
      super.visitMethodCallExpression(call)
    }
  }
}
