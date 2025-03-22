package com.bqbbo.tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class GenerateAst {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: generate_ast <output directory>");
            System.exit(64);
        }

        String outputDir = args[0];

        defineAst(outputDir, "Expr", Arrays.asList(
                "Assign : Token name, Expr value",
                "Binary : Expr left, Token operator, Expr right",
                "Grouping : Expr expression",
                "Literal : Object value",
                "Variable : Token name",
                "Unary : Token operator, Expr right"));

        defineAst(outputDir, "Stmt", Arrays.asList(
                "Expression : Expr expression",
                "Var : Token name, Expr initializer",
                "Print : Expr expression"));
    }

    // Defines the AST file under abstract class 'basename'
    private static void defineAst(String outputDir, String baseName, List<String> types) throws IOException {
        String fullPath = outputDir + "/" + baseName + ".java";
        PrintWriter fileWriter = new PrintWriter(fullPath, "UTF-8");

        fileWriter.println("package com.bqbbo.lox;");
        fileWriter.println();
        fileWriter.println("import java.util.List;");
        fileWriter.println("abstract class " + baseName + " {");

        defineVisitor(fileWriter, baseName, types);

        for (String type : types) {
            String className = type.split(":")[0].trim();
            String fieldList = type.split(":")[1].trim();

            defineType(fileWriter, baseName, className, fieldList);
        }

        fileWriter.println();
        fileWriter.println("    abstract <R> R accept(Visitor<R> visitor);");

        fileWriter.println("}");
        fileWriter.println();
        fileWriter.close();

    }

    // Defines a visitor method for each 'Expr.[TypeName]'
    private static void defineVisitor(PrintWriter fileWriter, String baseName, List<String> types) {
        fileWriter.println("    interface Visitor<R> {");

        for (String type : types) {
            String typeName = type.split(":")[0].trim();
            fileWriter.println(
                    "        R visit" + typeName + baseName + "(" + typeName + " " + baseName.toLowerCase() + ");");
        }

        fileWriter.println("    }");
    }

    // Writes subclasses under `baseClass` to the AST file
    private static void defineType(PrintWriter fileWriter, String baseName, String className, String fieldList) {
        fileWriter.println("    static class " + className + " extends " + baseName + " {");
        fileWriter.println("        " + className + "(" + fieldList + ") {");

        String[] fields = fieldList.split(", ");
        for (String field : fields) {
            String name = field.split(" ")[1];
            fileWriter.println("            this." + name + " = " + name + ";");
        }

        fileWriter.println("        }");
        fileWriter.println();

        fileWriter.println("        @Override");
        fileWriter.println("        <R> R accept(Visitor<R> visitor) {");
        fileWriter.println("            return visitor.visit" + className + baseName + "(this);");
        fileWriter.println("        }");

        fileWriter.println();

        for (String field : fields) {
            fileWriter.println("        final " + field + ";");
        }

        fileWriter.println("    }");
    }
}
