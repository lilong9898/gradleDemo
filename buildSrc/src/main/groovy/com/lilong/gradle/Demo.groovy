package com.lilong.gradle

import com.lilong.gradle.plugin.DemoTest
import org.gradle.api.Plugin
import org.gradle.api.Project

class Demo implements Plugin<Project> {

    @Override
    void apply(Project project) {
        new DemoTest().apply(project);
    }
}
