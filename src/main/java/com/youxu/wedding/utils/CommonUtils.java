package com.youxu.wedding.utils;

import org.springframework.boot.system.ApplicationHome;

import java.io.File;

public class CommonUtils {

    private static ApplicationHome appHome = new ApplicationHome(CommonUtils.class);

    public static String getJarHomePath(){
        File jarFile = appHome.getSource();
        return jarFile.getParentFile().toString();
    }

}
