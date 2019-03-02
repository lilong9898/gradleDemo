# Gradle脚本实现provided方式依赖远程project
## 问题由来
- 在Android中开发插件时,插件如需依赖某些远程库,有可能主工程也依赖这些库,比如官方的support库com.android.support:design:xxx
- 这时要求插件构建过程中不能带入这些库的代码,要在运行时使用主工程中的
- 插件为了编译通过,需要以provided方式依赖这些库的代码,而Android Gradle插件不允许provided方式依赖远程project
- 最原始的做法是,手动将这些库的代码打入jar包中,然后provided方式依赖这个jar包
- 但这个做法太费力了,每当远程库有更新,就需要手动重复上面的过程
## 演示
![](./1.png)  
这个external-support-lib-jars目录里,存放着全部"com.android.support:design:26.0.0"远程project所包含的代码的jar包
## 如何配置
- 在build.gradle的dependencies{}块之前,创建一个新的configuration名叫providedExternalModule:
```groovy
   configurations {
      providedExternalModule.setTransitive(true)
   }
   
   dependencies {
   ....
```  
- 在build.gradle的dependencies{}块中,将想以provided方式依赖的远程库,用providedExternalModule方式依赖:
```groovy
   dependencies {
      ....
      providedExternalModule "com.android.support:design:26.0.0"
      ....
   }
```
- 在build.gradle的最后,引入本脚本:
```groovy
   apply from: "provided_project.gradle"
```
- 最后,也是最简单的一步,拷贝脚本provided_project.gradle到build.gradle的同级目录
