package info.metadude.android.typedpreferences;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(RobolectricTestRunner.class)
public class DoublePreferenceTest {

    protected static final String PREFERENCES_KEY =
            "info.metadude.android.typedpreferences.TEST_KEY_DOUBLE";
    protected DoublePreference mPreference;
    protected final double mTestValue = 23.23d;
    protected final double mDefaultValue = 42.42f;
    protected final float mDelta = 0f;

    @Before
    public void beforeEach() {
        final SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(Robolectric.application);
        mPreference = new DoublePreference(sharedPreferences, PREFERENCES_KEY, mDefaultValue);
    }

    @Test
    public void expect_Preference_To_BeInitialized() throws Exception {
        assertNotNull(mPreference);
    }

    @Test
    public void expect_Preference_To_EqualDefaultValue() throws Exception {
        final SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(Robolectric.application);
        DoublePreference preference = new DoublePreference(sharedPreferences, PREFERENCES_KEY);
        assertEquals(preference.get(), DoublePreference.DEFAULT_VALUE_VALUE, mDelta);
    }

    @Test
    public void expect_Preference_NotTo_BeSet() throws Exception {
        assertFalse(mPreference.isSet());
    }

    @Test
    public void expect_Preference_To_BeSet() throws Exception {
        mPreference.set(mTestValue);
        assertTrue(mPreference.isSet());
    }

    @Test
    public void expect_Preference_To_EqualDefaultValue_BeforeBeingSet() throws Exception {
        final double value = mPreference.get();
        assertEquals(value, mDefaultValue, mDelta);
        assertNotEquals(value, mTestValue);
    }

    @Test
    public void expect_Preference_To_EqualValue() throws Exception {
        mPreference.set(mTestValue);
        final double value = mPreference.get();
        assertEquals(value, mTestValue, mDelta);
    }

    @Test
    public void expect_Preference_NotTo_EqualValue() throws Exception {
        mPreference.set(66.66d);
        assertNotEquals(mPreference.get(), mTestValue);
    }

    @Test
    public void expect_Preference_To_BeUnset() throws Exception {
        mPreference.set(mTestValue);
        mPreference.delete();
        assertNotEquals(mPreference.get(), mTestValue);
    }

}
