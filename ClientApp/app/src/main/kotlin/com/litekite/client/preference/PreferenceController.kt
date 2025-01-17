/*
 * Copyright 2020 LiteKite Startup. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.litekite.client.preference

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A Shared Preference Controller that uses Credential Protected Storage and it can be only
 * accessed after credentials for lock-screen being entered and unlocked.
 *
 * These preferences are stored in /data/user[release-version] or in /data/user-de[debug-version]
 *
 * @author Vignesh S
 * @version 1.0, 09/04/2020
 * @since 1.0
 */
@Suppress("UNUSED")
@Singleton
class PreferenceController @Inject constructor(private val context: Context) {

	companion object {
		const val PREFERENCES_CLIENT_APP = "preferences_client_app"

		const val PREFERENCE_LOGIN_COMPLETE_STATE = "preference_login_complete_state"

		const val PREFERENCE_LOGGED_IN_USER_ID = "preference_logged_in_user_id"
	}

	private val preferences = getPreferences()
	private val editor = getEditor()

	fun getBoolean(key: String): Boolean {
		return preferences.getBoolean(key, false)
	}

	fun getInt(key: String): Int {
		return preferences.getInt(key, 0)
	}

	fun getLong(key: String): Long {
		return preferences.getLong(key, 0)
	}

	fun getFloat(key: String): Float {
		return preferences.getFloat(key, 0F)
	}

	fun getDouble(key: String): Double {
		return java.lang.Double.longBitsToDouble(
			preferences.getLong(key, 0)
		)
	}

	fun getString(key: String): String {
		return preferences.getString(key, "") ?: ""
	}

	fun store(key: String, value: Boolean) {
		editor.putBoolean(key, value).apply()
	}

	fun store(key: String, value: Int) {
		editor.putInt(key, value).apply()
	}

	fun store(key: String, value: Long) {
		editor.putLong(
			key,
			value
		).apply()
	}

	fun store(key: String, value: Float) {
		editor.putFloat(key, value).apply()
	}

	fun store(key: String, value: Double) {
		editor.putLong(
			key,
			java.lang.Double.doubleToRawLongBits((value))
		).apply()
	}

	fun store(key: String, value: String) {
		editor.putString(key, value).apply()
	}

	private fun getEditor(): SharedPreferences.Editor {
		return preferences.edit()
	}

	private fun getPreferences(): SharedPreferences {
		return context.createCredentialProtectedStorageContext().getSharedPreferences(
			PREFERENCES_CLIENT_APP,
			Context.MODE_PRIVATE
		)
	}

}