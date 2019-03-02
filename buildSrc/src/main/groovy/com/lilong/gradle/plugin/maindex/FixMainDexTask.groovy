package com.lilong.gradle.plugin.maindex

import com.android.build.gradle.api.ApkVariant
import com.lilong.gradle.maindex.FixMainDexUtil
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

import java.util.function.Consumer

class FixMainDexTask extends DefaultTask {

    /** 键：flavor + buildType，如"OppoDebug", 值：这个flavor + buildType对应的所有类的列表（包括build/intermediates/classes里的和jar包里的）*/
    HashMap<String, List<String>> allClassNamesMap

    void setAllClassNamesMap(HashMap<String, List<String>> allClassNamesMap){
        this.allClassNamesMap = allClassNamesMap
    }

    @TaskAction
    void fixMainDex() {
        project.android.applicationVariants.all { ApkVariant variant ->
//            println "=======${variant}"
            File mainDexListFile = new File(project.buildDir.getAbsolutePath() + File.separator + "intermediates" + File.separator + "multi-dex" + File.separator + variant.flavorName + File.separator + variant.buildType.name + File.separator + "maindexlist.txt");
            if(mainDexListFile.exists() && mainDexListFile.isFile() && allClassNamesMap.containsKey(variant.flavorName.capitalize() + variant.buildType.name.capitalize())){

                List<String> allClassNames = allClassNamesMap.get(variant.flavorName.capitalize() + variant.buildType.name.capitalize())

                // 读取maindexlist.txt中的所有类名到列表中
                ArrayList<String> mainDexListClassNames = new ArrayList<String>();
                FileReader fr = new FileReader(mainDexListFile)
                String line = null;
                while((line = fr.readLine()) != null){
                    mainDexListClassNames.add(line);
                }
                fr.close()

                // 上面列表中的类按字母顺序排序
                mainDexListClassNames.sort()
                // 打印上面列表
//                mainDexListClassNames.forEach(new Consumer<String>() {
//                    @Override
//                    void accept(String s) {
//                        project.logger.lifecycle("---${s}")
//                    }
//                });
                
                // 读取build/intermediates/classes目录下所有类的类名到列表中
                File classesDir = new File(project.buildDir.getAbsolutePath() + File.separator + "intermediates" + File.separator + "classes" + File.separator + variant.flavorName + File.separator + variant.buildType.name)
                FixMainDexUtil.traverseFileTreeByDFS(classesDir, new FixMainDexUtil.OnFileTraversedCallback() {
                    @Override
                    void onFileTraversed(File f) {
                        allClassNames.add(f.getAbsolutePath().replace(classesDir.getAbsolutePath() + File.separator, ""))
                    }
                })

                // 上面列表按字母顺序排序
                allClassNames.sort()
                // 打印上面列表
//                allClassNames.forEach(new Consumer<String>() {
//                    @Override
//                    void accept(String s) {
//                        project.logger.lifecycle("+++${s}")
//                    }
//                })

                // 将maindexlist.txt中出现的非内部类的类名，在所有类的类名列表中找到，然后往前查找有没有内部类
                ArrayList<String> addedToMainDexList = new ArrayList<>();
                for(String mainDexListClassName : mainDexListClassNames){
                    if(!mainDexListClassName.contains("\$")){
                        int indexInAllClasses = allClassNames.indexOf(mainDexListClassName)
                        if(indexInAllClasses >= 0){
                            indexInAllClasses = indexInAllClasses - 1;
                            while(indexInAllClasses >= 0 && allClassNames.get(indexInAllClasses).contains(mainDexListClassName.replace(".class", "") + "\$")){
                                if(!mainDexListClassNames.contains(allClassNames.get(indexInAllClasses))){
                                    addedToMainDexList.add(allClassNames.get(indexInAllClasses))
                                }
                                indexInAllClasses = indexInAllClasses - 1;
                            }
                        }
                    }
                }

                // 打印最终要加到maindexlist里的类
                project.logger.lifecycle("--------要加到maindexlist.txt中的类----------")
                addedToMainDexList.forEach(new Consumer<String>() {
                    @Override
                    void accept(String s) {
                        project.logger.lifecycle("${s}")
                    }
                })
                project.logger.lifecycle("--------------------------------------------")

                // 将要加入到maindexlist.txt的类名加入进去
                FileWriter fw = new FileWriter(mainDexListFile, true)
                for(String addedToMainDexListClassName : addedToMainDexList){
                    fw.write("\n" + addedToMainDexListClassName)
                }
                fw.close()
            }
        }
    }
}