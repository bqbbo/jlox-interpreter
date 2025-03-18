package com.bqbbo.lox;

class AstPrinter implements Expr.Visitor<String> {
    /*
     * public static void main(String[] args) {
     * Expr expression = new Expr.Binary(
     * new Expr.Unary(new Token(TokenType.MINUS, "-", null, 1), new
     * Expr.Literal(123)),
     * new Token(TokenType.STAR, "*", null, 1), new Expr.Grouping(new
     * Expr.Literal(42.2)));
     *
     * System.out.println(new AstPrinter().print(expression));
     * }
     */

    String print(Expr expr) {
        return expr.accept(this);
    }

    // Override 'visitor' Interface methods for printing

    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        return parenthesize(expr.operator.lexeme, expr.left, expr.right);
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        return parenthesize("GROUPING", expr.expression);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.value == null) {
            return "nil";
        }

        return expr.value.toString();
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return parenthesize(expr.operator.lexeme, expr.right);
    }

    // Group productions for debugging
    private String parenthesize(String name, Expr... exprs) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("(").append(name);
        for (Expr expr : exprs) {
            stringBuilder.append(" ");
            stringBuilder.append(expr.accept(this));
        }
        stringBuilder.append(")");

        return stringBuilder.toString();
    }
}
