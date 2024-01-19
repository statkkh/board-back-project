package com.kkh.boardback.dto.response;

public interface ResponseCode {
    // 
    String SUCCESS = "SU";
    String VALIDATION_FAILED = "VF";
    String DUPLICATED_EMAIL = "DE";
    String DUPLICATED_NICKNAME = "DN";  
    String DUPLICATED_TELNUMBER= "DT";
    String DATABASE_ERROR = "DBE";
    String SIGN_IN_FAILED = "SF";
    String NOT_EXIST_USER = "NU";

    String DUPLICATED_ID = "DI";
    String MAIL_FAIL = "MF";
    String CERTIFICATION_FAIL = "CF";
}
