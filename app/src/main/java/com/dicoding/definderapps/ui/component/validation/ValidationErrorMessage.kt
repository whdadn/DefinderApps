package com.dicoding.definderapps.ui.component.validation

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.dicoding.definderapps.R

@Composable
fun ValidationErrorMessage(error:String){
    Text(
        text = error,
        style =MaterialTheme.typography.bodySmall.copy(),
        color = MaterialTheme.colorScheme.error
    )
}



@Composable
fun ErrorEmailMessage(isErrorEmail:String){
    when(isErrorEmail){
        "empty"->{
            ValidationErrorMessage(error = stringResource(id = R.string.email_is_empty))
        }
        "not_valid"->{
            ValidationErrorMessage(error = stringResource(id = R.string.email_not_valid))
        }
        else->{}
    }
}

@Composable
fun ErrorNameMessage(isErrorName:String){
    when(isErrorName){
        "empty"->{
            ValidationErrorMessage(error = stringResource(id = R.string.name_is_empty))
        }
        "less_than_three"->{
            ValidationErrorMessage(error = stringResource(id = R.string.name_less_than_three))
        }
        "not_valid"->{
            ValidationErrorMessage(error = stringResource(id = R.string.name_not_valid))
        }
        else->{}
    }
}

@Composable
fun ErrorPasswordMessage(isErrorPass:String){
    when(isErrorPass){
        "empty"->{
            ValidationErrorMessage(error = stringResource(id = R.string.pass_is_empty))
        }
        "less_than_eight"->{
            ValidationErrorMessage(error = stringResource(id = R.string.pass_less_than_eight))
        }
        "not_has_uppercase"->{
            ValidationErrorMessage(error = stringResource(id = R.string.pass_not_has_uppercase))
        }
        "not_has_lowercase"->{
            ValidationErrorMessage(error = stringResource(id = R.string.pass_not_has_lowercase))
        }
        "not_has_digit"->{
            ValidationErrorMessage(error = stringResource(id = R.string.pass_not_has_digit))
        }
        else->{}
    }
}