package com.soffice.projects.dataprovider.providers;

import com.soffice.datadriven.BaseProvider;
import com.soffice.projects.dataprovider.DataPath;
import com.soffice.projects.dataprovider.model.LoginModel;
import com.soffice.utils.configloader.JsonUtils;
import lombok.Getter;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

@Getter
public class LoginProvider extends BaseProvider {
    JsonUtils jsonUtils = JsonUtils.getInstance();

    @DataProvider(name = "TK_Login_001_Valid")
    public Object[][] TK_Login_001_Valid(Method method) {
        var dataList = jsonUtils.readDataTestFromJSON(DataPath.DATA_LOGIN, method.getName());

        LoginModel templateModel = new LoginModel();
        return updateDataModel(templateModel, dataList);
    }
}
