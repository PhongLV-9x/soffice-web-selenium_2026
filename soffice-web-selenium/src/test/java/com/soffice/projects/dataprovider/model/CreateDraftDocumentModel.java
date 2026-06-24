package com.soffice.projects.dataprovider.model;

import com.soffice.datadriven.BaseModel;
import com.soffice.datadriven.DataModel;
import lombok.Getter;

@Getter
public class CreateDraftDocumentModel extends BaseModel {
    public DataModel summary;          // Trích yếu
    public DataModel documentBook;     // Sổ văn bản dự kiến
    public DataModel distributionType; // Loại BH (Nội bộ, ...)
    public DataModel issuingPlace;     // Nơi ban hành (Phòng / Nhóm)
    public DataModel issuingUnit;      // Đơn vị/Nhóm ban hành
    public DataModel documentType;     // Loại VB
    public DataModel classification;  // Phân loại
    public DataModel remark;           // Ghi chú

    public CreateDraftDocumentModel() {
        super();
        summary = createDataModelObj("Summary");
        documentBook = createDataModelObj("DocumentBook");
        distributionType = createDataModelObj("DistributionType");
        issuingPlace = createDataModelObj("IssuingPlace");
        issuingUnit = createDataModelObj("IssuingUnit");
        documentType = createDataModelObj("DocumentType");
        classification = createDataModelObj("Classification");
        remark = createDataModelObj("Remark");
    }
}
