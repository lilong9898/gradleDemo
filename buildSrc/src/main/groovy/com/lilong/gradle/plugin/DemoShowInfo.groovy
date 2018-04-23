package com.lilong.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project;

class DemoShowInfo implements Plugin<Project> {

    @Override
    void apply(Project project) {

        /** project名字*/
        project.logger.lifecycle "===project.name : ${project.name}==="

        /** project的相对路径*/
        project.logger.lifecycle "===project.path : ${project.path}==="

        /** project的*/
        project.logger.lifecycle "===project.buildDir.path : ${project.buildDir.path}==="
        project.logger.lifecycle "===project.buildDir.absolutePath : ${project.buildDir.absolutePath}==="
        project.logger.lifecycle "===project.buildFile.absolutePath : ${project.buildFile.absolutePath}==="

        project.logger.lifecycle "===project.parent.name : ${project.parent.name}==="
    }

}