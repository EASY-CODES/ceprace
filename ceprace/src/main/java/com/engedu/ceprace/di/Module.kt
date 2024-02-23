package com.engedu.ceprace.di

import com.engedu.ceprace.data.remote.OpenCepApiService
import com.engedu.ceprace.data.remote.PostmonApiService
import com.engedu.ceprace.data.remote.ViaCepApiService
import com.engedu.ceprace.data.remote.WidnetApiService
import com.engedu.ceprace.data.repository.AddressRepository
import com.example.cepraceapp.data.repository.AddressRepositoryImpl
import com.engedu.ceprace.domain.useCase.GetAddressUseCaseImpl
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

internal val viaCepModule = module {
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://viacep.com.br/ws/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ViaCepApiService::class.java)
    }
}

internal val openCepModule = module {
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://opencep.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(OpenCepApiService::class.java)
    }
}

internal val postmonModule = module {
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.postmon.com.br/v1/cep/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(PostmonApiService::class.java)
    }
}

internal val widenetModule = module {
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://apps.widenet.com.br/busca-cep/api/cep/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(WidnetApiService::class.java)
    }
}

internal val appModule = module {
    single<AddressRepository> { AddressRepositoryImpl(get(), get(), get(), get()) }
    factory { GetAddressUseCaseImpl(get()) }
}

internal val myModules =
    listOf(viaCepModule, openCepModule, postmonModule, widenetModule, appModule)




