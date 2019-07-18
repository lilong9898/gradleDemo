package com.lilong.gradle;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
    // 写成extends AppCompatActivity能编过
    // 但运行时崩溃，因为找不到android.support.v7.app.AppCompatActivity这个类，以provided方式依赖，它并没被打进包里

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 报错，因为最终以provided方式依赖的都是support包中的jar包，只有代码，没有资源
//        int attrSupportColorPrimary = R.attr.colorPrimary;
        // 使用framework中的资源则没有问题
        int attrColorPrimary = android.R.attr.colorPrimary;
    }
}
