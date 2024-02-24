package com.engedu.ceprace

import com.engedu.ceprace.data.model.EstadoInfoDTO
import com.engedu.ceprace.data.model.OpenCepDTO
import com.engedu.ceprace.data.model.PostmonDTO
import com.engedu.ceprace.data.model.ViaCepDTO
import com.engedu.ceprace.data.model.WidnetDTO
import com.engedu.ceprace.data.remote.OpenCepApiService
import com.engedu.ceprace.data.remote.PostmonApiService
import com.engedu.ceprace.data.remote.ViaCepApiService
import com.engedu.ceprace.data.remote.WidnetApiService
import com.engedu.ceprace.data.repository.AddressRepository
import com.engedu.ceprace.data.repository.AddressRepositoryImpl
import com.engedu.ceprace.domain.model.toVo
import com.engedu.ceprace.domain.useCase.GetAddressUseCaseImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetAddressUseCaseImplTest {

    private val viaCepApiService: ViaCepApiService = mockk()
    private val openCepApiService: OpenCepApiService = mockk()
    private val postmonApiService: PostmonApiService = mockk()
    private val widnetApiService: WidnetApiService = mockk()
    private lateinit var addressRepositoryImplTest: AddressRepository
    private lateinit var useCaseImpl: GetAddressUseCaseImpl
    private val viaCepDTO = ViaCepDTO("rua cd 18", "marilucy", "tucurui", "pa")

    @Before
    fun setup() {
        //Arrange
        coEvery { viaCepApiService.getAddress(any()) } coAnswers {
            delay(1000L)
            viaCepDTO
        }

        coEvery {
            postmonApiService.getAddress(any())
        } coAnswers {
            delay(2000L)
            mockk()
        }

        coEvery {
            openCepApiService.getAddress(any())
        } coAnswers {
            delay(3000L)
            mockk()
        }

        coEvery {
            widnetApiService.getAddress(any())
        } coAnswers {
            delay(4000L)
            mockk()
        }

        addressRepositoryImplTest = AddressRepositoryImpl(
            viaCepApiService,
            openCepApiService,
            postmonApiService,
            widnetApiService
        )

        useCaseImpl = GetAddressUseCaseImpl(addressRepositoryImplTest)
    }


    @Test
    fun mytest1() = runTest {
        //Action
        val result = useCaseImpl.execute("123")

        //Assert
        Assert.assertEquals(result, viaCepDTO.toVo())
    }


}