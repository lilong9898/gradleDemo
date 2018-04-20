package com.lilong.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project;

class Demo implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println "==========haha=========="
    }
}
