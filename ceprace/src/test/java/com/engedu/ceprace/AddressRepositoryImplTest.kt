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
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class AddressRepositoryImplTest {

    private val viaCepApiService: ViaCepApiService = mockk()
    private val openCepApiService: OpenCepApiService = mockk()
    private val postmonApiService: PostmonApiService = mockk()
    private val widnetApiService: WidnetApiService = mockk()
    private val addressRepositoryImplTest: AddressRepository = AddressRepositoryImpl(
        viaCepApiService,
        openCepApiService,
        postmonApiService,
        widnetApiService
    )

    @Test
    fun getAddressViaCepSuccessReturnViaCepDTO() = runTest {
        //Arrange
        coEvery { viaCepApiService.getAddress(any()) } returns ViaCepDTO("", "", "", "")

        //Action
        val result = addressRepositoryImplTest.getAddressViaCep("123")

        //Assert
        assertEquals(result.javaClass, ViaCepDTO::class.java) //verifica se retora uma instancia correta
        coVerify { viaCepApiService.getAddress("123") } //verifica se internamente foi chamdo o getAddress do service ao chamar o getAddress do repository
        coVerify(exactly = 1) { viaCepApiService.getAddress("123")  } //verifica quantas vezes o metodo foi chamado
    }

    @Test
    fun getAddressPostmomSuccessReturnPostmomDTO() = runTest {
        //Arrange
        coEvery { postmonApiService.getAddress(any()) } returns PostmonDTO(
            "",
            "",
            "",
            EstadoInfoDTO("")
        )

        //Action
        val result = addressRepositoryImplTest.getAddressPostmon("123")

        //Assert
        assertEquals(result.javaClass, PostmonDTO::class.java)
        coVerify { postmonApiService.getAddress("123") }
    }

    @Test
    fun getAddressOpenCepSuccessReturnOpenCepDTO() = runTest {
        //Arrange
        coEvery { openCepApiService.getAddress(any()) } returns OpenCepDTO("", "", "", "")

        //Action
        val result = addressRepositoryImplTest.getAddressOpenCep("123")

        //Assert
        assertEquals(result.javaClass, OpenCepDTO::class.java)
        coVerify { openCepApiService.getAddress("123") }
    }

    @Test
    fun getAddressWidnetSuccessReturnWidnetDTO() = runTest {
        //Arrange
        coEvery { widnetApiService.getAddress(any()) } returns WidnetDTO("", "", "", "")

        //Action
        val result = addressRepositoryImplTest.getAddressWidnet("123")

        //Assert
        assertEquals(result.javaClass, WidnetDTO::class.java)
        coVerify { widnetApiService.getAddress("123") }
    }


}