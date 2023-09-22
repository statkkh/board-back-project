package com.kkh.boardback.dto.response;

public interface ResponseMessage {
   
    String VALIDATION_FAILED = "Validation Failed.";
    String SUCCESS = "Succss.";
    String DUPLICATED_EMAIL = "Duplicated email.";
    String DUPLICATED_NICKNAME = "Duplicated nickname.";
    String DUPLICATED_TELNUMBER = "Duplicated telNumber number";
    
    String DATABASE_ERROR = "DataBase error.";
}
