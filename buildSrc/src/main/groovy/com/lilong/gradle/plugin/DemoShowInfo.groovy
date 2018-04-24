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

        /** project的build目录*/
        project.logger.lifecycle "===project.buildDir.path : ${project.buildDir.path}==="

        /** project的build目录的绝对路径*/
        project.logger.lifecycle "===project.buildDir.absolutePath : ${project.buildDir.absolutePath}==="

        /** project的build.gradle文件的绝对路径*/
        project.logger.lifecycle "===project.buildFile.absolutePath : ${project.buildFile.absolutePath}==="

        /** project的上级project的名字*/
        project.logger.lifecycle "===project.parent.name : ${project.parent.name}==="

        /** project的defaultTask名字*/
        for (taskName in project.defaultTasks){
            project.logger.lifecycle("===project.defaultTasks name : ${taskName}===")
        }
    }

}