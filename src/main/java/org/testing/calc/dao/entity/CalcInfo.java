package org.testing.calc.dao.entity;

public class CalcInfo {
    private String key;
    private String operation;
    private int operand1;
    private int operand2;

    public CalcInfo(String key, String operation, int operand1, int operand2) {
        this.key = key;
        this.operation = operation;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public int getOperand1() {
        return operand1;
    }

    public void setOperand1(int operand1) {
        this.operand1 = operand1;
    }

    public int getOperand2() {
        return operand2;
    }

    public void setOperand2(int operand2) {
        this.operand2 = operand2;
    }
}

