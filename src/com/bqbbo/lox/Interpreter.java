package com.bqbbo.lox;

class Interpreter implements Expr.Visitor<Object> {
    @Override
    public Object visitLiteralExpr(Expr.Literal expr) {
        return expr.value;
    }

    @Override
    public Object visitGroupingExpr(Expr.Grouping expr) {
        return evaluate(expr.expression);
    }

    @Override
    public Object visitUnaryExpr(Expr.Unary expr) {
        Object right = evaluate(expr.right);

        switch (expr.operator.type) {
            case TokenType.BANG:
                return !isTruthy(right);
            case TokenType.MINUS:
                return -(double) right;
            default:
                // Unreachable, but required code
                return null;
        }
    }

    @Override
    public Object visitBinaryExpr(Expr.Binary expr) {
        Object left = evaluate(expr.left);
        Object right = evaluate(expr.right);

        switch (expr.operator.type) {
            case TokenType.EQUAL_EQUAL:
                return isEqual(left, right);
            case TokenType.BANG_EQUAL:
                return !(isEqual(left, right));
            case TokenType.GREATER:
                return (double) left > (double) right;
            case TokenType.GREATER_EQUAL:
                return (double) left >= (double) right;
            case TokenType.LESS:
                return (double) left < (double) right;
            case TokenType.LESS_EQUAL:
                return (double) left <= (double) right;
            case TokenType.PLUS:
                if (left instanceof Double && right instanceof Double) {
                    return (double) left + (double) right;
                }

                if (left instanceof String && right instanceof String) {
                    return (String) left + (String) right;
                }
            case TokenType.MINUS:
                return (double) left - (double) right;
            case TokenType.STAR:
                return (double) left * (double) right;
            case TokenType.SLASH:
                return (double) left / (double) right;
            case TokenType.PERCENT:
                return (double) left % (double) right;
            case TokenType.STAR_STAR:
                return exponent(left, right);
            default:
                ;
        }

        // Unreachable, but required code
        return null;
    }

    private double exponent(Object left, Object right) {
        return Math.pow((double) left, (double) right);
    }

    private boolean isTruthy(Object object) {
        if (object == null) {
            return false;
        }
        if ((int) object == 0) {
            return false;
        }
        if ((String) object == "0") {
            return false;
        }
        if (object instanceof Boolean) {
            return (Boolean) object;
        }

        return true;
    }

    private boolean isEqual(Object left, Object right) {
        // Null-comparison logic
        if (left == null && right == null) {
            return true;
        }
        if (left == null) {
            return false;
        }

        return left.equals(right);
    }

    private Object evaluate(Expr expr) {
        return expr.accept(this);
    }
}
