package com.soffice.projects.dataprovider.providers;

import com.soffice.datadriven.BaseProvider;
import com.soffice.projects.dataprovider.DataPath;
import com.soffice.projects.dataprovider.model.CreateDraftDocumentModel;
import com.soffice.utils.configloader.JsonUtils;
import lombok.Getter;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

@Getter
public class CreateDraftDocumentProvider extends BaseProvider {
    JsonUtils jsonUtils = JsonUtils.getInstance();

    @DataProvider(name = "TK_CreateDraftDocument_001_Valid")
    public Object[][] TK_CreateDraftDocument_001_Valid(Method method) {
        var dataList = jsonUtils.readDataTestFromJSON(DataPath.DATA_CREATE_DRAFT_DOCUMENT, method.getName());

        CreateDraftDocumentModel templateModel = new CreateDraftDocumentModel();
        return updateDataModel(templateModel, dataList);
    }
}
