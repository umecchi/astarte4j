package jp.umecchi.astarte4j.api.entities.apps

import com.google.gson.annotations.SerializedName

class AppRegistration(
    @SerializedName("secret")
    var AppSecret: String = "") {
}
