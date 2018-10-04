import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.builder.AstBuilder

println "runtime compiled string"
List<ASTNode> astNodes = new AstBuilder().buildFromString("println x")
for (n in astNodes) {
    println n
}

println "\nclosure"
astNodes = new AstBuilder().buildFromCode( { int from, int to, int ask -> return from <= ask && ask < to } )

for (n in astNodes) {
    println n
}

println "closure regenerated from AST"
for (node in astNodes) {
    java.io.StringWriter writer = new java.io.StringWriter();
    groovy.inspect.swingui.AstNodeToScriptVisitor visitor = new groovy.inspect.swingui.AstNodeToScriptVisitor(writer);
    visitor.visitReturnStatement(node); // replace with proper visit****
    System.out.println(writer.toString());

}