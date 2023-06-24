package com.example.opscpoe3

//(Color Picker Android Studio Kotlin Custom Spinner Tutorial, 2021)
class ColorList
{
    private val whiteHex = "FFFFFF"

    val defaultColor : ColorObject = basicColors()[0]

    //(Color Picker Android Studio Kotlin Custom Spinner Tutorial, 2021)
    fun colorPosition(colorObject: ColorObject):Int
    {
//(Color Picker Android Studio Kotlin Custom Spinner Tutorial, 2021)
        for(i in basicColors().indices){
            if(colorObject == basicColors()[i])
                return i
        }
        return 0
    }

    //(Color Picker Android Studio Kotlin Custom Spinner Tutorial, 2021)
    fun basicColors():List<ColorObject>
    {
        return listOf(
            ColorObject("Light Brown", "F8EDE3", whiteHex),
            ColorObject("Blue", "86A3B8", whiteHex),
            ColorObject("Red", "A84448", whiteHex),
            ColorObject("Purple", "975C8D", whiteHex),
            ColorObject("Green", "ABC4AA", whiteHex),
        )
    }
}
//Color Picker Android Studio Kotlin Custom Spinner Tutorial. (2021, October 8). [Video]. Youtube. Retrieved June 6, 2023, from https://www.youtube.com/watch?v=YsKjl8ZbM4g