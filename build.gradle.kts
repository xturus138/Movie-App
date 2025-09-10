
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
}

extra["lifecycle_version"] = "2.6.0-alpha01"
extra["retrofit_version"] = "2.9.0"
extra["glide_version"] = "4.16.0"