package io;


/**
 * 目录过滤器
 */

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class DirFilter implements FilenameFilter {
    /**
     * 用于正则表达式过滤
     */
    private Pattern pattern;

    public DirFilter(String regex) {
        pattern = Pattern.compile(regex);
    }

    //该接口的唯一方法
    @Override
    public boolean accept(File dir, String name) {
        return pattern.matcher(name).matches();
    }
}
