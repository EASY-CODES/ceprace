<p align="center">
<img src="https://github.com/EASY-CODES/ceprace/blob/main/app/src/main/res/drawable/ic_rance.png" width="5%"/>
</p>

# CEP RACE [![JitPack](https://jitpack.io/v/EASY-CODES/ceprace.svg)](https://jitpack.io/#EASY-CODES/ceprace) 

CepRace creates four flows that simultaneously call four different APIs (ViaCep, OpenCep, Postmon and Widnet) that look for addresses using the CEP. The first API that returns successfully presents the result and the rest of the flow is cancelled.
I solved this problem using Flow's features, such as `flatMapMerge{}`, a Kotlin Flow operator that allows combining multiple flows into a single flow. I handled success cases using `filter` and performed mapping using `map{}` to transform into the desired output object. Finally, with `.first()`, I get the first successful response.
## Solving problem 

```gradle
= flow {
    val combinedFlow = flowOf(viaCepFlow, openCepFlow, postmonFlow, widnetFlow)
        .flatMapMerge { it }.filter { it.success }.map { it.addressVO }
    combinedFlow.collect { emit(it) }
}.first()
```
## Gradle

Add the code below to your project's root `settins.gradle` file

```gradle
repositories {
//other codes
 maven("https://jitpack.io")
}
```

And add a dependency `gradle.gradle`
```gradle 
dependencies {
    implementation("com.github.EASY-CODES:ceprace:v1.0.7")
}
```

## Basic Usage
Add in you **ViewModel** file.

```kotlin
CoroutineScope(Dispatchers.IO).launch {
    val addressVO: AddressVO? = CepRaceInit.execute("68458370")
     Log.d("CEP_RACE", addressVO.toString())
}
```

## Screnshot example app use CepRace
<p align="center">
<img src="https://github.com/EASY-CODES/ceprace/blob/main/app/src/main/res/drawable/print.png" width="30%"/>
</p>

## License
```
    Copyright 2024 Eduardo Lima Nadciemento

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
```
