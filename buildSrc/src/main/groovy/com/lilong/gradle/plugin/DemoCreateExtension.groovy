package com.lilong.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project;

class DemoCreateExtension implements Plugin<Project>{

    @Override
    void apply(Project project) {
        project.extensions.create("DemoExtension", )
    }
}

class DemoExtension {
    String demoString;
}