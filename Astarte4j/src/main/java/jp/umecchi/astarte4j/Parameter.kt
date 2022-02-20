package jp.umecchi.astarte4j

import org.json.JSONArray
import org.json.JSONObject

class Parameter {
    private val parameters = JSONObject()
    fun append(key: String, value: String): Parameter {
        parameters.put(key, value)
        return this
    }

    fun append(key: String, value: Int): Parameter {
        parameters.put(key, value)
        return this
    }

    fun append(key: String, value: Long): Parameter {
        parameters.put(key, value)
        return this
    }

    fun append(key: String, value: Boolean): Parameter {
        parameters.put(key, value)
        return this
    }

    fun append(key: String, value: List<String>): Parameter {
        parameters.put(key, value)
        return this
    }

    fun append(key: String, value: JSONArray): Parameter {
        parameters.putOpt(key, value)
        return this
    }

    fun build(): String? = parameters.toString()
}
