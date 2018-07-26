## 如何使用JsBridge?

### Js调用Android原生

* **对于Android开发者**

1. 创建Api功能类

如需新增一个新的功能类提供给Js调用，则自定义一个类并实现JsApi接口，然后在此类中定义具有一个JsContext形参的方法，并通过@JsAnnotation注解此方法，以表明此方法是提供给Js调用。

```java
public class LogJsApi implements JsApi {

    @JsAnnotation
    public void d(JsContext jsContext) {
        Log.d(jsContext.get("tag", "undefine"), jsContext.get("msg", "undefine"));
    }
    
    @JsAnnotation
    public void i(JsContext jsContext) {
        Log.i(jsContext.get("tag", "undefine"), jsContext.get("msg", "undefine"));
    }
}
```

2. 在JsApiMapping中声明

在JsApiMapping类中的buildMapping()方法里通过mApiMapping.put(*功能名称*, *功能类*)新增一个功能类。

```java
public class JsApiMapping {
    private static HashMap<String, Class<? extends JsApi>> mApiMapping;

    private JsApiMapping() {

    }

    public static HashMap<String, Class<? extends JsApi>> getApiMapping() {
        if (mApiMapping == null) {
            synchronized (JsApiMapping.class) {
                if (mApiMapping == null) {
                    mApiMapping = new HashMap<>();
                    buildMapping();
                }
            }
        }
        return mApiMapping;
    }

    private static void buildMapping() {
        mApiMapping.put("log", LogJsApi.class);
        mApiMapping.put("dialog", DialogJsApi.class);
        mApiMapping.put("nativeJs", NativeJsApi.class);
    }
}
```

完成以上步骤之后，在程序运行时会根据 功能名称  和其中的方法名来生成一个可供js调用的函数，如：

```javascript
jsApi.log.d(arg)
```

而调用函数需要传递的参数应根据功能类提供者所定义的参数格式为主。


3. 方法回调

当提供给Js的方法被调用时，会传入一个JsContext对象的实参，此实参可以通过get()和put()方法进行数据的读取和写入，也可通过调用这个对象的success(),cancel(),error()方法来回调Js。



* **对于Web开发者**

1. 调用Android原生代码

Web开发人员可以通过如下方式调用原生代码：

```javascript
jsApi.log.d({
    tag : 'wsy',
    msg : arg
})
```

同时也可以通过另外一种形式调用：

```javascript
function log(level, arg) {
    if (level == 0) {
        jsApi.log.d({
            tag : 'wsy',
            msg : arg
        })
    } else if (level == 1) {
        jsApi.log.i({
            tag : 'cmf',
            msg : arg
        })
    }
}
```

Web开发者可以根据需求自行灵活运用。

2. 方法回调实现

当需要和Android原生代码之间进行交互的时候，就需要通过回调实现，但回调是否生效还需要看功能类提供者是否实现了回调功能。

Js中方法回调的书写形式如下：

```javascript
function show() {
    jsApi.dialog.show({
        title : "警告",
        message : "请不要点击取消!",
        positive : "知道了",
        negative : "我就要",
        success : function(arg) {
            alert(arg.msg);
        },
        cancel : function(arg) {
            alert(arg.msg);
        },
        error : function(arg, err) {
            alert(err);
        }
    })
}
```

参数中具体返回的数据主要由功能类提供者所决定。



### Android原生调用Js

* **对于Android开发者**

1. 通过KWebView的nativeJs方法进行调用Js函数

nativeJs方法有三个形参。第一个形参是指Js的函数名；第二个形参是指传递给Js的数据；第三个形参是指回调的处理。

```java
findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        JsContext jsContext = new JsContext(kwv.getWebViewProxy());
        kwv.nativeJs("test", jsContext, new NativeCallback() {
            @Override
            public void success(JsContext jsContext) {
                Log.d("cmf", "success...");
            }

            @Override
            public void cancel(JsContext jsContext) {
                Log.d("cmf", "cancel...");
            }

            @Override
            public void error(JsContext jsContext) {
                Log.d("cmf", "error...");
            }
        });
    }
});
```

2. 方法回调

如步骤1中调用nativeJs()方法时的第三个形参，只需要在其中处理回调即可。最终是否能被成功回调，需由Web开发者实现。



* **对于Web开发者**

1. 定义一个函数

如果需要添加一个给Android原生代码调用的函数，则需要在代码中进行定义。如下：

```javascript
function test(arg, rtn) {
    rtn.success(arg);
}
```

函数体内可根据具体需求来实现。其中，第一个形参是指Android原生代码传过来的数据；第二个形参是指回调函数的对象，里面包含了success()，cancel()和error()三个回调方法。



## 不足之处

JsBridge库中，暂未实现监听的功能，目前仅限于一次回调，如Js调用Android原生代码后回调了一次之后，第二次再调用回调方法将不会再生效，因为此时回调的对象已经移除出回调的集合了。



## 关于我

邮箱: webmaster@klavor.com<br />
扣扣: 2262693733<br />
微博: http://weibo.com/lihuan1991<br />
主页: https://www.klavor.com