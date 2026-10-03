package com.calc.service;

import com.calc.entity.CalcHistory;
import com.calc.repository.CalcHistoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Stack;

@Service
public class CalcService {

    private final CalcHistoryRepository calcHistoryRepository;

    public CalcService(CalcHistoryRepository calcHistoryRepository) {
        this.calcHistoryRepository = calcHistoryRepository;
    }

    public String calculate(String expr) {
        try {
            double result = evaluateExpression(expr);
            String resStr;
            // 去掉.0 例如 10.0 →10
            if(result == Math.floor(result)){
                resStr = String.valueOf((long)result);
            }else{
                resStr = String.valueOf(result);
            }
            CalcHistory record = new CalcHistory(expr, resStr, LocalDateTime.now());
            calcHistoryRepository.save(record);
            return resStr;
        }catch (Exception e){
            return "表达式错误";
        }
    }

    // 计算数学表达式，支持 +-*/ 和小数
    private double evaluateExpression(String expression) {
        Stack<Double> numStack = new Stack<>();
        Stack<Character> opStack = new Stack<>();
        StringBuilder numBuf = new StringBuilder();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (Character.isDigit(c) || c == '.') {
                numBuf.append(c);
            } else {
                if (!numBuf.isEmpty()) {
                    numStack.push(Double.parseDouble(numBuf.toString()));
                    numBuf.setLength(0);
                }
                while (!opStack.isEmpty() && getPriority(opStack.peek()) >= getPriority(c)) {
                    calc(numStack, opStack.pop());
                }
                opStack.push(c);
            }
        }
        if (!numBuf.isEmpty()) {
            numStack.push(Double.parseDouble(numBuf.toString()));
        }
        while (!opStack.isEmpty()) {
            calc(numStack, opStack.pop());
        }
        return numStack.pop();
    }

    private int getPriority(char op) {
        return switch (op) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            default -> 0;
        };
    }

    private void calc(Stack<Double> numStack, char op) {
        double b = numStack.pop();
        double a = numStack.pop();
        double res = switch (op) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> {
                if (b == 0) throw new ArithmeticException("除零");
                yield a / b;
            }
            default -> throw new RuntimeException("非法运算符");
        };
        numStack.push(res);
    }

    public List<CalcHistory> getAllHistory() {
        return calcHistoryRepository.findAll();
    }

    public void clearHistory() {
        calcHistoryRepository.deleteAll();
    }
}
