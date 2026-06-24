package com.soffice.consts;

import lombok.Getter;
import lombok.Setter;

import static com.soffice.consts.FrameConst.AppConfig.APP_DOMAIN;

@Getter
@Setter
public class ProjectConst {

    /* Add the exact path for each module here once confirmed from the real system,
     * then use BasePage.goToSpecificURL(module.getPath(), module.getName()) to navigate directly by URL. */
    @Getter
    public enum ModuleURL {
        HOME("Trang chủ", APP_DOMAIN + "app/main/home"),
        QUAN_TRI_HE_THONG("Quản trị Hệ thống", APP_DOMAIN + "app/main/system"),
        QUAN_TRI_DANH_MUC("Quản trị Danh mục", APP_DOMAIN + "app/main/category"),
        VAN_BAN_DEN("Văn bản đến", APP_DOMAIN + "app/main/incoming-document"),
        VAN_BAN_DI("Văn bản đi", APP_DOMAIN + "app/main/outgoing-document"),
        VAN_BAN_LUU_TRU("Văn bản lưu trữ", APP_DOMAIN + "app/main/archived-document"),
        VAN_BAN_DU_THAO("Văn bản dự thảo", APP_DOMAIN + "app/main/draft-document");

        private final String name;
        private final String path;

        ModuleURL(String name, String path) {
            this.name = name;
            this.path = path;
        }
    }
}
