package com.dicoding.definderapps

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.definderapps.di.Injection
import com.dicoding.definderapps.repository.Repository
import com.dicoding.definderapps.ui.detail.DetailViewModel
import com.dicoding.definderapps.ui.favorite.favoritetourism.FavoriteViewModel
import com.dicoding.definderapps.ui.home.HomeViewModel
import com.dicoding.definderapps.ui.login.LoginViewModel
import com.dicoding.definderapps.ui.profile.ProfileViewModel
import com.dicoding.definderapps.ui.register.RegisterViewModel
import com.dicoding.definderapps.ui.search.SearchViewModel
import com.dicoding.definderapps.ui.welcome.WelcomeViewModel

class ViewModelFactory(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(RegisterViewModel::class.java)->{
                RegisterViewModel(repository) as T
            }
            modelClass.isAssignableFrom(LoginViewModel::class.java)->{
                LoginViewModel(repository) as T
            }
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java)->{
                DetailViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SearchViewModel::class.java)->{
                SearchViewModel(repository) as T
            }
            modelClass.isAssignableFrom(FavoriteViewModel::class.java)->{
                FavoriteViewModel(repository) as T
            }
            modelClass.isAssignableFrom(ProfileViewModel::class.java)->{
                ProfileViewModel(repository) as T
            }
            modelClass.isAssignableFrom(WelcomeViewModel::class.java)->{
                WelcomeViewModel(repository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}