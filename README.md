# 🏁 A Corrida pelo CEP (um estudo aplicado de flow) 🏁

Recentemente, tenho me aprofundado bastante em Coroutines e Flow do Kotlin. Inspirado por uma ideia que surgiu ao assistir a um dos vídeos do Filipe Deschamps de 2020, onde ele fala sobre sua biblioteca CEP Promise ([link](https://lnkd.in/dFFAmrKw)), decidi me desafiar e resolver o mesmo problema, mas desta vez em Kotlin Android usando Flow.

Basicamente, crio quatro fluxos que chamam simultaneamente quatro APIs diferentes (ViaCep, OpenCep, Postmon e Widnet), e a primeira que retornar com sucesso apresenta o resultado.

Resolvi esse problema utilizando os recursos do Flow, como `flatMapMerge{}`, um operador do Kotlin Flow que permite combinar vários fluxos em um único fluxo. Tratei os casos de sucesso usando `filter` e realizei a mapeamento usando `map{}` para transformar no objeto de saída desejado. Por fim, com `.first()`, obtenho a primeira resposta bem-sucedida.

# CepRace [![JitPack](https://jitpack.io/v/EASY-CODES/ceprace.svg)](https://jitpack.io/#EASY-CODES/ceprace)

CepRace cria quatro fluxos que chamam simultaneamente quatro APIs (ViaCep, OpenCep, Postmon e Widnet) diferentes que buscam endereços através do CEP. A primeira API que retornar com sucesso apresenta o resultado e o restante do fluxo é cancelado.

## Screenshots
<p align="center">
<img src="https://user-images.githubusercontent.com/26925002/162633413-8cc80819-5ff8-4258-b60e-5101d058c907.png" width="30%"/>
</p>


## Gradle

Adicione o código abaixo ao arquivo `build.gradle` raiz do seu projeto (não ao arquivo `build.gradle` do módulo).

```gradle
allprojects {
    repositories {
        // Outros repositórios...
        maven { url 'https://jitpack.io' }
    }
}
```

And add a dependency code to your **module**'s `build.gradle` file.
```gradle
dependencies {
        implementation("com.github.EASY-CODES:ceprace:v1.0.7")
}
```

## Basic Usage
Add in you Activity file.

```kotlin
CoroutineScope(Dispatchers.IO).launch {
    val addressVO: AddressVO? = CepRaceInit.execute("68458370")
     Log.d("CEP_RACE", addressVO.toString())
}
```

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
