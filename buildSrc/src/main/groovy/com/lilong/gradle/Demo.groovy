package com.lilong.gradle

import com.lilong.gradle.plugin.DemoCreateExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class Demo implements Plugin<Project> {

    @Override
    void apply(Project project) {
//        new DemoShowLifeCycle().apply(project)
//        new DemoShowInfo().apply(project)
        new DemoCreateExtension().apply(project)
    }
}
