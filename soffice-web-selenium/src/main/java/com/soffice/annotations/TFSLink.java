package com.soffice.annotations;

import java.lang.annotation.*;

/**
 * Class for linking the bug management system
 * @author vincent
 */
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface TFSLink {
    String value();
}