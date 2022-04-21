# LG_DesensitizeText
Desensitize EditText





```kotlin
val et: EditText = findViewById(R.id.et)
val et2: EditText = findViewById(R.id.et2)
val et3: EditText = findViewById(R.id.et3)
//init DesensitizeTransformationMethod() => 朱 -> 朱
et.transformationMethod = DesensitizeTransformationMethod()
//DesensitizeTransformationMethod(4, 14) => 330741199908295219 -> 3307**********5219
et2.transformationMethod = DesensitizeTransformationMethod(4, 14)
//DesensitizeTransformationMethod(3, 7) => 18458234508 -> 184****4508
et3.transformationMethod = DesensitizeTransformationMethod(3, 7)
//
et.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                if (s.isNullOrEmpty()) {
                    return
                }
                et.removeTextChangedListener(this)
                when {
                    s.length > 2 -> {
                        //DesensitizeTransformationMethod(1, 2) => 方凯翔 -> 方*翔
                        //DesensitizeTransformationMethod(1, 3) => 诸葛孔明 -> 诸**明
                        et.transformationMethod = DesensitizeTransformationMethod(1, s.length - 1)
                    }
                    s.length == 2 -> {
                        //DesensitizeTransformationMethod(0, 1) => 朱凯 -> *凯
                        et.transformationMethod = DesensitizeTransformationMethod(0, 1)
                    }
                    else -> {
                        //DesensitizeTransformationMethod() => 朱 -> 朱
                        et.transformationMethod = DesensitizeTransformationMethod()
                    }
                }
                et.setSelection(et.text.length)
                et.addTextChangedListener(this)
            }
        })


```



![1](https://github.com/louisgeek/LG_DesensitizeText/blob/main/screenshots/pic.png)

![2](https://github.com/louisgeek/LG_DesensitizeText/blob/main/screenshots/pic2.png)

![3](https://github.com/louisgeek/LG_DesensitizeText/blob/main/screenshots/pic3.png)

![4](https://github.com/louisgeek/LG_DesensitizeText/blob/main/screenshots/pic4.png)

![5](https://github.com/louisgeek/LG_DesensitizeText/blob/main/screenshots/pic5.png)



![](https://s2.loli.net/2022/04/22/1QkvNgG7F9VAUCI.png)

![pic2](https://s2.loli.net/2022/04/22/vaO6HVZb1Djc5NP.png)

![pic3](https://s2.loli.net/2022/04/22/eGD5wZivT7pjqJy.png)

![pic4](https://s2.loli.net/2022/04/22/kLH39mKQYGz6aIq.png)

![pic5](https://s2.loli.net/2022/04/22/8AsciCbMZgIVpJ9.png)
