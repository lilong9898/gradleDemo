package com.lilong.gradle

import com.lilong.gradle.plugin.DemoCreateExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

// 插件的两大作用：
// 1. 对project进行已有的操作
// 2. 通过定义extension对project增加新的功能
class Demo implements Plugin<Project> {

    @Override
    void apply(Project project) {
//        new DemoShowLifeCycle().apply(project)
//        new DemoShowInfo().apply(project)
        new DemoCreateExtension().apply(project)
    }
}
