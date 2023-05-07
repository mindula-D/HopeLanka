package com.example.text.activities
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.*
import org.junit.Before
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import com.example.text.R
import org.junit.After

class PackageCheckTest {
    private lateinit var packageCheck: PackageCheck

    @Before
    fun setup() {
        packageCheck = PackageCheck()
    }

    @Test
    fun packageNameGivenAsSameString_returnsTrue() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = packageCheck.isEqual(context, R.string.app_name, "Text")
        assertThat(result).isTrue() }

    @Test
    fun packageNameDifferentAsGivenString_returnsFalse() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = packageCheck.isEqual(context, R.string.app_name, "Dilshika")
        assertThat(result).isFalse()
    }

    @After
    fun tearDown() {

    }
}
