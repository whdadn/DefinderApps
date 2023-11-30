package com.dicoding.definderapps.ui.component.validation

fun nameValidation(name:String):String{
    if (!name.isNotEmpty()){
        return "empty"
    }else if (name.length<3){
        return "less_than_three"
    }
    for (n in name) {
        if (!n.isLetter() && n != ' ') {
            return "not_valid"
        }
    }
    return ""
}

fun usernameValidation(username:String):String{
    val regex = Regex("^[a-zA-Z0-9._]+$")
    if (!username.isNotEmpty()){
        return "empty"
    }else if (username.length<4){
        return "less_than_four"
    }else if(username.length>15){
        return "more_than_fifteen"
    }else if (!username.matches(regex)){
        return "not_valid"
    }else{
        return ""
    }

}

fun emailValidation(email: String): String {
    val regex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
    if (!email.isNotEmpty()) {
        return "empty"
    } else if (!email.matches(regex)) {
        return "not_valid"
    } else {
        return ""
    }

}

fun passwordValidation(password: String):String{
    val hasUppercase = password.any { it.isUpperCase() }
    val hasLowercase = password.any { it.isLowerCase() }
    val hasDigit = password.any { it.isDigit() }

    if (!password.isNotEmpty()){
        return "empty"
    }else if (password.length < 8) {
        return "less_than_eight"
    }else if (!hasUppercase) {
        return "not_has_uppercase"
    }else if (!hasLowercase) {
        return "not_has_lowercase"
    }else if (!hasDigit) {
        return "not_has_digit"
    }else{
        return ""
    }
}