package com.ilhomsoliev.tipscounter


fun strToTotalAmount(str:String) :String{
    var ans = ""
    for(i in str.indices){
        if(str[i] == '.'){
            if(i + 2 < str.length){
                ans += ".${str[i + 1]}${str[i + 2]}"
                break
            }else {
                if(str[i + 1] != '0'){
                    ans += ".${str[i + 1]}0"
                }
                break
            }
        }else ans += str[i]
    }
    return ans
}