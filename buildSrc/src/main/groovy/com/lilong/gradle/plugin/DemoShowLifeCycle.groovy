package com.lilong.gradle.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project;

class DemoShowLifeCycle implements Plugin<Project> {

    @Override
    void apply(Project project) {

        /**
         * apply方法会在目标工程的apply plugin: com.lilong.gradle.Demo处调用
         * 在init和config阶段，build.gradle中所有语句是按顺序执行的，
         * 也就是通过按顺序执行这些语句来完成init和config
         * execute阶段则是执行某个具体的task
         * */
        project.logger.lifecycle "===plugin apply called==="

        /**
         * 未发现有执行到
         * */
        project.beforeEvaluate {
            project.logger.lifecycle "===project.beforeEvaluate==="
        }

        /**
         init和config阶段完成，即将进入execute阶段时执行
         */
        project.afterEvaluate {
            project.logger.lifecycle "===project.afterEvaluate==="
        }

        /**
         * 在afterEvaluate的closure之前执行，因为在config阶段中，evaluate操作会评估出所有的buildVariant
         * */
        project.android.applicationVariants.all {applicationVariant ->
            project.logger.lifecycle "===project.android.applicationVariants.all variant = ${applicationVariant.name}==="
        }

        /**
         * afterEvaluate之后执行
         * */
        project.afterEvaluate {
            project.android.applicationVariants.all {applicationVariant ->
                project.logger.lifecycle "===project.afterEvaluate {project.android.applicationVariants.all variant} = ${applicationVariant.name}==="
            }
        }
    }
}