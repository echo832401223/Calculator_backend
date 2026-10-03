package com.calc.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "calc_history")
public class CalcHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 算式
    private String expression;
    // 结果
    private String result;
    // 计算时间
    private LocalDateTime createTime;

    public CalcHistory() {}

    public CalcHistory(String expression, String result, LocalDateTime createTime) {
        this.expression = expression;
        this.result = result;
        this.createTime = createTime;
    }

    // Getter
    public Long getId() {
        return id;
    }
    public String getExpression() {
        return expression;
    }
    public String getResult() {
        return result;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    // Setter
    public void setId(Long id) {
        this.id = id;
    }
    public void setExpression(String expression) {
        this.expression = expression;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
