package com.soffice.projects.dataprovider.model;

import com.soffice.datadriven.BaseModel;
import com.soffice.datadriven.DataModel;
import lombok.Getter;

@Getter
public class LoginModel extends BaseModel {
    public DataModel userName;
    public DataModel password;

    public LoginModel() {
        super();
        userName = createDataModelObj("UserName");
        password = createDataModelObj("Password");
    }
}
