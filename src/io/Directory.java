package io;

import sun.reflect.generics.tree.Tree;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public final class Directory {
    /**
     *
     * @param dir
     * @param regex
     * @return 符合该目录下所有符合正则表达式的文件合集
     */
    public static File[] local(File dir, final String regex) {
        return dir.listFiles(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);
            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(new File(name).getName()).matches();
            }
        });
    }

    public static File[] local(String dir, final String regex) {
        return local(new File(dir), regex);
    }

    /**
     * 适用于文件树， 上面的local方法只适用于单层的文件
     */
    public static class TreeInfo implements Iterable<File> {
        public List<File> files = new ArrayList<>();
        public List<File> dirs = new ArrayList<>();

        @Override
        public Iterator<File> iterator() {
            return files.iterator();
        }

        public void addAll(TreeInfo other) {
            files.addAll(other.files);
            dirs.addAll(other.dirs);
        }

        public static TreeInfo recurseDirs(File startDir, String regex) {
            TreeInfo result = new TreeInfo();
            for (File item : startDir.listFiles()) {
                if (item.isDirectory()) {
                    result.dirs.add(item);
                    result.addAll(recurseDirs(item, regex));
                } else {
                    if (item.getName().matches(regex)) {
                        result.files.add(item);
                    }
                }
            }
            return result;
        }

        public static TreeInfo walk(File startDir, String regex) {
            return recurseDirs(startDir, regex);
        }

        public static TreeInfo walk(String startDir, String regex) {
            return recurseDirs(new File(startDir), regex);
        }

        public static TreeInfo walk(String startDir) {
            return walk(startDir, ".*");
        }



    }


}
