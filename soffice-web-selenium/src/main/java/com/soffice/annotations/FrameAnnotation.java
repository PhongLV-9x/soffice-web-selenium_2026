package com.soffice.annotations;
import com.soffice.consts.AuthorType;
import com.soffice.consts.FrameConst.CategoryType;

import javax.annotation.Nullable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class for test script annotation
 * @author vincent
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface FrameAnnotation {
    CategoryType[] category();

    AuthorType[] author();

    @Nullable
    AuthorType[] reviewer();
}
