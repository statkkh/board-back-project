package com.kkh.boardback.dto.response;

public interface ResponseMessage {
    
    String VALIDATION_FAILED = "Validation Failed.";
    String SUCCESS = "Succss.";
    String DUPLICATED_EMAIL = "Duplicated email.";
    String DUPLICATED_NICKNAME = "Duplicated nickname.";
    String DUPLICATED_TELNUMBER = "Duplicated telNumber number";
    String SIGN_IN_FAILED = "Login information mismatch";
    String DATABASE_ERROR = "DataBase error.";
    String NOT_EXIST_USER = "This user does exits";

    String DUPLICATED_ID = "Duplicated Id";
    String MAIL_FAIL = "Mail send failed";
}
