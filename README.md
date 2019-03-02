# Gradle脚本实现provided方式依赖远程project
## 问题由来
- 在开发插件时,插件如需依赖某些远程库,有可能主工程也依赖这些库,比如官方的support库com.android.support:design:xxx
- 这时要求插件构建过程中不能带入这些库的代码,要在运行时使用主工程中的
- 插件为了编译通过,需要以provided方式依赖这些库的代码,而Android Gradle插件不允许provided方式依赖远程project
- 最原始的做法是,手动将这些库的代码打入jar包中,然后provided方式依赖这个jar包
- 但这个做法太费力了,每当远程库有更新,就需要手动重复上面的过程
## 本脚本的能力:全自动处理
- 
