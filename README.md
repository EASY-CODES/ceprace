# 🏁 A Corrida pelo CEP (um estudo aplicado de flow) 🏁

Recentemente, tenho me aprofundado bastante em Coroutines e Flow do Kotlin. Inspirado por uma ideia que surgiu ao assistir a um dos vídeos do Filipe Deschamps de 2020, onde ele fala sobre sua biblioteca CEP Promise ([link](https://lnkd.in/dFFAmrKw)), decidi me desafiar e resolver o mesmo problema, mas desta vez em Kotlin Android usando Flow.

Basicamente, crio quatro fluxos que chamam simultaneamente quatro APIs diferentes (ViaCep, OpenCep, Postmon e Widnet), e a primeira que retornar com sucesso apresenta o resultado.

Resolvi esse problema utilizando os recursos do Flow, como `flatMapMerge{}`, um operador do Kotlin Flow que permite combinar vários fluxos em um único fluxo. Tratei os casos de sucesso usando `filter` e realizei a mapeamento usando `map{}` para transformar no objeto de saída desejado. Por fim, com `.first()`, obtenho a primeira resposta bem-sucedida.

# CepRace [![JitPack](https://jitpack.io/v/EASY-CODES/ceprace.svg)](https://jitpack.io/#EASY-CODES/ceprace)

CepRace cria quatro fluxos que chamam simultaneamente quatro APIs (ViaCep, OpenCep, Postmon e Widnet) diferentes que buscam endereços através do CEP. A primeira API que retornar com sucesso apresenta o resultado e o restante do fluxo é cancelado.

## Gradle

Adicione o código abaixo ao arquivo `build.gradle` raiz do seu projeto (não ao arquivo `build.gradle` do módulo).

```gradle
allprojects {
    repositories {
        // Outros repositórios...
        maven { url 'https://jitpack.io' }
    }
}
