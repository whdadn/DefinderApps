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


fun emailValidation(email: String): String {
    val regex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
    return if (!email.isNotEmpty()) {
        "empty"
    } else if (!email.matches(regex)) {
        "not_valid"
    } else {
        ""
    }

}

fun passwordValidation(password: String):String{
    val hasUppercase = password.any { it.isUpperCase() }
    val hasLowercase = password.any { it.isLowerCase() }
    val hasDigit = password.any { it.isDigit() }

    return if (!password.isNotEmpty()){
        "empty"
    }else if (password.length < 8) {
        "less_than_eight"
    }else if (!hasUppercase) {
        "not_has_uppercase"
    }else if (!hasLowercase) {
        "not_has_lowercase"
    }else if (!hasDigit) {
        "not_has_digit"
    }else{
        ""
    }
}