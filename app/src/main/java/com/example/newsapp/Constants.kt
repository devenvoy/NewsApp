package com.example.newsapp

import android.content.Context.MODE_PRIVATE

class Constants {

    companion object {
        //        val BASE_URL = "https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=a323558c5a6d419087f29a5db79f87ae"
        const val BASE_URL = "https://newsapi.org/"
        const val API_KEY = "a323558c5a6d419087f29a5db79f87ae"

        val countryMap = hashMapOf(
            "India" to "in",
            "United Arab Emirates" to "ae",
            "Argentina" to "ar",
            "Austria" to "at",
            "Australia" to "au",
            "Belgium" to "be",
            "Bulgaria" to "bg",
            "Brazil" to "br",
            "Canada" to "ca",
            "Switzerland" to "ch",
            "China" to "cn",
            "Colombia" to "co",
            "Cuba" to "cu",
            "Czech Republic" to "cz",
            "Germany" to "de",
            "Egypt" to "eg",
            "France" to "fr",
            "United Kingdom" to "gb",
            "Greece" to "gr",
            "Hong Kong" to "hk",
            "Hungary" to "hu",
            "Indonesia" to "id",
            "Ireland" to "ie",
            "Israel" to "il",
            "Italy" to "it",
            "Japan" to "jp",
            "South Korea" to "kr",
            "Lithuania" to "lt",
            "Latvia" to "lv",
            "Morocco" to "ma",
            "Mexico" to "mx",
            "Malaysia" to "my",
            "Nigeria" to "ng",
            "Netherlands" to "nl",
            "Norway" to "no",
            "New Zealand" to "nz",
            "Philippines" to "ph",
            "Poland" to "pl",
            "Portugal" to "pt",
            "Romania" to "ro",
            "Serbia" to "rs",
            "Russia" to "ru",
            "Saudi Arabia" to "sa",
            "Sweden" to "se",
            "Singapore" to "sg",
            "Slovenia" to "si",
            "Slovakia" to "sk",
            "Thailand" to "th",
            "Turkey" to "tr",
            "Taiwan" to "tw",
            "Ukraine" to "ua",
            "United States" to "us",
            "Venezuela" to "ve",
            "South Africa" to "za"
        )

        val country = arrayOf(
            "India",
            "Malaysia",
            "Portugal",
            "Greece",
            "Austria",
            "South Korea",
            "Latvia",
            "Morocco",
            "Brazil",
            "Slovenia",
            "Colombia",
            "Argentina",
            "Hungary",
            "Japan",
            "Ukraine",
            "New Zealand",
            "Canada",
            "Turkey",
            "Belgium",
            "Taiwan",
            "Italy",
            "South Africa",
            "Germany",
            "Singapore",
            "Hong Kong",
            "United States",
            "Egypt",
            "Thailand",
            "Russia",
            "Saudi Arabia",
            "Netherlands",
            "Sweden",
            "China",
            "Ireland",
            "Poland",
            "Slovakia",
            "Bulgaria",
            "France",
            "Lithuania",
            "Nigeria",
            "Serbia",
            "Romania",
            "Philippines",
            "United Kingdom",
            "United Arab Emirates",
            "Switzerland",
            "Cuba",
            "Venezuela",
            "Czech Republic",
            "Norway",
            "Mexico",
            "Israel",
            "Australia",
            "Indonesia"
        )

        val categoryList = arrayListOf(
            "entertainment",
            "business",
            "general",
            "health",
            "science",
            "sports",
            "technology"
        )

        var selectedCountry : String? = "India"
        var selectedCategory: String? = null
    }
}