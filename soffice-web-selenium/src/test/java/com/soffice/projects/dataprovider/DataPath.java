package com.soffice.projects.dataprovider;

import com.soffice.consts.FrameConst;

public interface DataPath {
    String env = FrameConst.ExecuteConfig.EXE_ENV.toLowerCase();

    String DATA_LOGIN = "data/" + env + "/json/loginData.json";
    String DATA_CREATE_DRAFT_DOCUMENT = "data/" + env + "/json/createDraftDocument.json";

    /* Add more data file paths here as new test cases are implemented */
}
