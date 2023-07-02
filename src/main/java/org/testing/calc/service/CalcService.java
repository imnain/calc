package org.testing.calc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.testing.calc.dao.entity.Calc;
import org.testing.calc.dao.entity.CalcInfo;
import org.testing.calc.dao.repository.CalcRepository;

@Service
public class CalcService {

    private final CalcRepository calcRepository;

    @Autowired
    public CalcService(CalcRepository calcRepository) {
        this.calcRepository = calcRepository;
    }
    public void saveCalcData(CalcInfo calcInfo) {
        validateCalcInfo(calcInfo);
        int result = calculateResult(calcInfo.getOperation(), calcInfo.getOperand1(), calcInfo.getOperand2());
        String stringKey = calcInfo.getKey();
        Calc calc = new Calc(stringKey, result);
        calcRepository.save(calc);
    }

    public Integer fetchCalcData(String keyValue) {
        Calc calc = calcRepository.findByKeyValue(keyValue);
        if(calc != null){
            return calc.getResult();
        }
        return null;
    }

    private int calculateResult(String operation, int operand1, int operand2) {
        if ("plus".equals(operation)) {
            return operand1 + operand2;
        } else if ("minus".equals(operation)) {
            return operand1 - operand2;
        } else {
            throw new IllegalArgumentException("Invalid operation");
        }
    }
    private void validateCalcInfo(CalcInfo calcInfo) {
        if (calcInfo == null) {
            throw new IllegalArgumentException("CalcInfo object is null");
        }
        String key = calcInfo.getKey();
        String operation = calcInfo.getOperation();
        Integer operand1 = calcInfo.getOperand1();
        Integer operand2 = calcInfo.getOperand2();

        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("Key cannot be empty or null");
        }

        if (operation == null || (!"plus".equals(operation) && !"minus".equals(operation))) {
            throw new IllegalArgumentException("Invalid operation. Only 'plus' or 'minus' is allowed.");
        }

        if (operand1 == null || operand2 == null) {
            throw new IllegalArgumentException("Operands cannot be null");
        }
    }
}
