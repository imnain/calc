package org.testing.calc.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.testing.calc.dao.entity.CalcInfo;
import org.testing.calc.service.CalcService;
import static org.mockito.Mockito.doNothing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CalcControllerTest {

    @Test
    public void testPostHappyPath() {
        CalcService calcService = Mockito.mock(CalcService.class);
        CalcController calcController = new CalcController(calcService);

        String key = "test_key";
        String operation = "add";
        int operand1 = 5;
        int operand2 = 10;

        CalcInfo calcInfoMock = new CalcInfo(key, operation, operand1, operand2);

        doNothing().when(calcService).saveCalcData(calcInfoMock);

        ResponseEntity<String> response = calcController.saveData(calcInfoMock);

        verify(calcService, times(1)).saveCalcData(calcInfoMock);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Data saved successfully.", response.getBody());
    }

    @Test
    public void testPostNotHappyPath() {
        CalcService calcService = Mockito.mock(CalcService.class);
        CalcController calcController = new CalcController(calcService);

        String key = "test_key";
        String operation = "divide"; // Invalid operation
        int operand1 = 5;
        int operand2 = 0; // Division by zero

        CalcInfo calcInfoMock = new CalcInfo(key, operation, operand1, operand2);

        doThrow(new IllegalArgumentException("Invalid operation or division by zero"))
                .when(calcService).saveCalcData(calcInfoMock);

        ResponseEntity<String> response = calcController.saveData(calcInfoMock);

        verify(calcService, times(1)).saveCalcData(calcInfoMock);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error saving data: Invalid operation or division by zero", response.getBody());
    }


}
