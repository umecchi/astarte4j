# astarte4j
NeoAstarte API library for kotlin

[![](https://jitpack.io/v/umecchi/astarte4j.svg)](https://jitpack.io/#umecchi/astarte4j)

# Official API Doc
https://note.com/preview/n8177517dd7a1?prev_access_key=c783c60c4919b6da942065140b4d5713#e74d2f62-acca-418c-9d92-b36de98f2119

# Get Started

astarte4j publish in jitpack.
Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

```groovy
implementation 'com.github.umecchi:astarte4j:Tag'
```

Check latest version on Jitpack [![](https://jitpack.io/v/umecchi/astarte4j.svg)](https://jitpack.io/#umecchi/astarte4j)

# Usage

## Get Public Timeline

__kotlin__

```kotlin
val client: AstarteClient = AstarteClient.Builder("server.url", OkHttpClient.Builder(), Gson())
    .accessToken(accessToken)
    .build()
        
val timelines = timelines(client)
val statuses: List<Status> = timelines.getPublic("newest_id","oldest_id").execute()
```