package com.rijaldev.githubuser.utils

import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.DialogFragmentNavigator
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController

object NavControllerHelper {
    fun Fragment.safeNavigate(navDirections: NavDirections, fragmentHostName: String) {
        val navController = findNavController()
        when (val destination = navController.currentDestination) {
            is FragmentNavigator.Destination -> {
                if (fragmentHostName == destination.className) {
                    navController.navigate(navDirections)
                }
            }
            is DialogFragmentNavigator.Destination -> {
                if (fragmentHostName == destination.className) {
                    navController.navigate(navDirections)
                }
            }
        }
    }
}