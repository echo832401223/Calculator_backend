package com.calc.controller;

import com.calc.entity.CalcHistory;
import com.calc.service.CalcService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*") // 允许前端网页跨域访问
public class CalcController {

    private final CalcService calcService;

    // 构造函数注入Service
    public CalcController(CalcService calcService) {
        this.calcService = calcService;
    }

    //计算接口 POST /api/calc

    @PostMapping("/calc")
    public Map<String,String> calculate(@RequestBody Map<String,String> body){
        String expr = body.get("expr");
        String result = calcService.calculate(expr);
        return Map.of("result", result);
    }

    //获取全部历史记录 GET /api/history
    @GetMapping("/history")
    public List<CalcHistory> getHistoryList(){
        return calcService.getAllHistory();
    }

    //清空全部历史记录 DELETE /api/history
    @DeleteMapping("/history")
    public Map<String,String> clearAllHistory(){
        calcService.clearHistory();
        return Map.of("msg","历史记录已清空");
    }
}
