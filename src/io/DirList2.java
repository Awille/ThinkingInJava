package io;

import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FilenameFilter;

public class DirList2 {
    public static FilenameFilter filter(String regex) {
        return new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(regex);
            }
        };
    }

    public static void main(String[] args) {
        File path = new File("src/io");
        String[] list;
        list = path.list(filter(".java"));
        for (String name : list) {
            System.out.println(name);
        }
    }
}
