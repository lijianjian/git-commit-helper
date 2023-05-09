package com.github.lijianjian.gitcommithelper.services

import com.github.lijianjian.gitcommithelper.configuration.PluginSettingsState
import com.google.gson.Gson
import com.google.gson.JsonObject
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils
import java.io.IOException

class JiraService {
    fun getSummary(jiraNumber: String?): String {
        try {
            val jiraAuthorization = PluginSettingsState.instance.state.jiraAuthorization
            HttpClients.createDefault().use { httpClient ->
                val httpGet = HttpGet("https://viljasolutions.atlassian.net/rest/api/2/issue/$jiraNumber")
                httpGet.setHeader("Accept", "application/json")
                httpGet.setHeader(
                    "Authorization",
                    jiraAuthorization
                )
                val httpResponse: HttpResponse = httpClient.execute(httpGet)
                val httpEntity = httpResponse.entity
                val string = EntityUtils.toString(httpEntity)
                val gson = Gson()
                val jsonObject = gson.fromJson(string, JsonObject::class.java)
                val summary = jsonObject["fields"].asJsonObject["summary"].asString
                val key = jsonObject["key"].asString
                return "$key $summary"
            }
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }


}