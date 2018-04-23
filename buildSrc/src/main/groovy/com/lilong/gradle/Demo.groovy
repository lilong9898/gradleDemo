package com.lilong.gradle

import com.lilong.gradle.plugin.DemoLifeCycle
import com.lilong.gradle.plugin.DemoShowInfo
import org.gradle.api.Plugin
import org.gradle.api.Project

class Demo implements Plugin<Project> {

    @Override
    void apply(Project project) {
        new DemoLifeCycle().apply(project)
        new DemoShowInfo().apply(project)
    }
}
