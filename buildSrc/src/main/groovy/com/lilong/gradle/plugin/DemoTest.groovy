package com.lilong.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project;

class DemoTest implements Plugin<Project> {

    @Override
    void apply(Project project) {
        println("===========apply============")
        project.afterEvaluate {
            println("===========afterEvaluate============")
        }
        project.beforeEvaluate {
            println("==============beforeEvaluate===========")
        }
    }
}