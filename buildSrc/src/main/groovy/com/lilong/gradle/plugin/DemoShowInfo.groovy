package com.lilong.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project;

class DemoShowInfo implements Plugin<Project> {

    @Override
    void apply(Project project) {
        project.logger.lifecycle("===project.name : ${project.name}===")
    }

}