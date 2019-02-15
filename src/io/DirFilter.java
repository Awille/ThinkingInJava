package io;


/**
 * 目录过滤器
 */

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class DirFilter implements FilenameFilter {
    //该接口的唯一方法
    private String regex;

    public DirFilter(String regex) {
        this.regex = regex;
    }

    @Override
    public boolean accept(File dir, String name) {
        if (name.endsWith(regex)) {
            return true;
        } else {
            return false;
        }
    }
}
