package com.soffice.projects.dataprovider.providers;

import com.soffice.datadriven.BaseProvider;
import com.soffice.projects.dataprovider.DataPath;
import com.soffice.projects.dataprovider.model.CreateDraftDocumentModel;
import com.soffice.projects.dataprovider.model.SignDocumentModel;
import com.soffice.utils.configloader.JsonUtils;
import lombok.Getter;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

@Getter
public class SignDocumentProvider extends BaseProvider {
    JsonUtils jsonUtils = JsonUtils.getInstance();

    @DataProvider(name = "TK_SignDocument_001_Valid")
    public Object[][] TK_SignDocument_001_Valid(Method method) {
        var dataList = jsonUtils.readDataTestFromJSON(DataPath.DATA_SIGN_DOCUMENT, method.getName());

        SignDocumentModel templateModel = new SignDocumentModel();
        return updateDataModel(templateModel, dataList);
    }
}
