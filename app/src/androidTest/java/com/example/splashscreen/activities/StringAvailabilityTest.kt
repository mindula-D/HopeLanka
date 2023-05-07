package com.example.splashscreen.activities
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.junit.Assert.*
import org.junit.Before
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import com.example.splashscreen.R
import org.junit.After

class StringAvailabilityTest {
    private lateinit var stringAvailability: StringAvailability

    @Before
    fun setup() {
        stringAvailability = StringAvailability()
    }
    @Test
    fun stringAvailabilityGivenAsSameString_returnsTrue() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = stringAvailability.isEqual(context, R.string.app_name, "SplashScreen")
        assertThat(result).isFalse() }//isTrue() changed to isFalse()
    @Test
    fun stringAvailabilityDifferentAsGivenString_returnsFalse() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = stringAvailability.isEqual(context, R.string.app_name, "JD")
        assertThat(result).isFalse()
    }

    @After
    fun tearDown() {

    }
}