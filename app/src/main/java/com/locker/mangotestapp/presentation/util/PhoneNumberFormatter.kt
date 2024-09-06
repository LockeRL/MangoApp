package com.locker.mangotestapp.presentation.util

import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber

fun formatPhoneNumber(countryCode: String, number: String): String? =
    try {
        val phoneUtil = PhoneNumberUtil.getInstance()
        val numberProto = phoneUtil.parse(number, countryCode)
        phoneUtil.format(numberProto, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL)
    } catch (e: Exception) {
        null
    }

fun formatAndStrip(countryCode: String, number: String): String =
    formatPhoneNumber(countryCode, number)?.let { stripCountryCode(it) } ?: number

fun maxPhoneLength(countryCode: String): Int {
    val phoneUtil = PhoneNumberUtil.getInstance()
    val exampleNumber =
        phoneUtil.getExampleNumberForType(countryCode, PhoneNumberUtil.PhoneNumberType.FIXED_LINE)
    return exampleNumber.nationalNumber.toString().length
}

fun getRegionFromNumber(phoneNumber: String): String? =
    try {
        val phoneUtil = PhoneNumberUtil.getInstance()
        val numberProto: Phonenumber.PhoneNumber = phoneUtil.parse(phoneNumber, null)
        phoneUtil.getRegionCodeForNumber(numberProto)
    } catch (e: NumberParseException) {
        null
    }



fun stripCountryCode(number: String): String =
    if (number.contains("+"))
        number.dropWhile { it != ' ' }.drop(1)
    else
        number

fun String.deleteNotDigits(): String = this.filter { it.isDigit() }
