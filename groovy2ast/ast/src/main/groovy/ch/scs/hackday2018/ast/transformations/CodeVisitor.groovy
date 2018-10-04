package ch.scs.hackday2018.ast.transformations

import org.codehaus.groovy.ast.CodeVisitorSupport
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.Parameter
import org.codehaus.groovy.ast.expr.*
import org.codehaus.groovy.ast.stmt.*

class CodeVisitor extends CodeVisitorSupport {

  private int intentation = 0;

  void printMethod(MethodNode method) {
    log("Method: $method.name")
    Statement code = method.code
    println(code.text)

    shift {
      log("Parameters")
      for (Parameter parameter : method.parameters) {
        shift {
          log("$parameter.name: ${parameter.type.name}")
        }
      }
      code.visit(this)
    }
  }

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
  void visitBinaryExpression(BinaryExpression expression) {
    log("Operation: ${expression.operation.text}")
    shift {
      log("Left")
      shift {
        expression.getLeftExpression().visit(this);
      }
    }
    shift {
      log("Right")
      shift {
        expression.getRightExpression().visit(this);
      }
    }
  }

  @Override
  void visitVariableExpression(VariableExpression expression) {
    log("Variable: $expression.name with type: $expression.type")
  }

  @Override
  void visitMethodCallExpression(MethodCallExpression call) {
    log("Call: $call.methodAsString")
    shift {
      log("On Object")
      shift {
        call.getObjectExpression().visit(this);
      }
      log("Method")
      shift {
        call.getMethod().visit(this);
      }
      log("Arguments")
      shift {
        call.getArguments().visit(this);
      }
    }
  }

  @Override
  void visitConstantExpression(ConstantExpression expression) {
    log("Constant: $expression.text")
    super.visitConstantExpression(expression)
  }
}
