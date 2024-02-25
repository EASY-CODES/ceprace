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

    //sut
    private lateinit var useCaseImpl: GetAddressUseCaseImpl

    //declare fake
    private lateinit var addressRepositoryImplTest: AddressRepository

    //define mocks
    private val viaCepApiService: ViaCepApiService = mockk()
    private val openCepApiService: OpenCepApiService = mockk()
    private val postmonApiService: PostmonApiService = mockk()
    private val widnetApiService: WidnetApiService = mockk()

    //define dummies
    private val viaCepDTO = ViaCepDTO("rua cd 18", "marilucy", "tucurui", "pa")
    private val openCepDTO = OpenCepDTO("rua cd 18", "marilucy", "tucurui", "pa")
    private val postmonDTO = PostmonDTO("rua cd 18", "marilucy", "tucurui", EstadoInfoDTO("pa"))
    private val widnetDTO = WidnetDTO("rua cd 18", "marilucy", "tucurui", "pa")

    @Test
    fun executeUseCaseWinnerViaCepReturnViaVCepDTO() = runTest {
        //Arrage
        defineWinner(Winner.ViaCep())

        //Action
        val result = useCaseImpl.execute("123")

        //Assert
        Assert.assertEquals(result, viaCepDTO.toVo())
    }

    @Test
    fun executeUseCaseWinnerOpenCepReturnOpenDTO() = runTest {
        //Arrage
        defineWinner(Winner.OpenCep())

        //Action
        val result = useCaseImpl.execute("123")

        //Assert
        Assert.assertEquals(result, openCepDTO.toVo())
    }


    @Test
    fun executeUseCaseWinnerPostmomReturnPostmomDTO() = runTest {
        //Arrage
        defineWinner(Winner.Postmom())

        //Action
        val result = useCaseImpl.execute("123")

        //Assert
        Assert.assertEquals(result, postmonDTO.toVo())
    }

    @Test
    fun executeUseCaseWinnerWidnetReturnWidentDTO() = runTest {
        //Arrage
        defineWinner(Winner.Widnet())

        //Action
        val result = useCaseImpl.execute("123")

        //Assert
        Assert.assertEquals(result, widnetDTO.toVo())
    }



    //define stubs
    private fun defineWinner(winner: Winner) {
        when (winner) {
            is Winner.ViaCep -> {
                coEvery { viaCepApiService.getAddress(any()) } coAnswers {
                    delay(1000L)
                    viaCepDTO
                }

                coEvery {
                    postmonApiService.getAddress(any())
                } coAnswers {
                    delay(2000L)
                    postmonDTO
                }

                coEvery {
                    openCepApiService.getAddress(any())
                } coAnswers {
                    delay(3000L)
                    openCepDTO
                }

                coEvery {
                    widnetApiService.getAddress(any())
                } coAnswers {
                    delay(4000L)
                    widnetDTO
                }
            }

            is Winner.OpenCep -> {
                coEvery { viaCepApiService.getAddress(any()) } coAnswers {
                    delay(3000L)
                    viaCepDTO
                }

                coEvery {
                    postmonApiService.getAddress(any())
                } coAnswers {
                    delay(2000L)
                    postmonDTO
                }

                coEvery {
                    openCepApiService.getAddress(any())
                } coAnswers {
                    delay(1000L)
                    openCepDTO
                }

                coEvery {
                    widnetApiService.getAddress(any())
                } coAnswers {
                    delay(4000L)
                    widnetDTO
                }
            }

            is Winner.Postmom -> {
                coEvery { viaCepApiService.getAddress(any()) } coAnswers {
                    delay(2000L)
                    viaCepDTO
                }

                coEvery {
                    postmonApiService.getAddress(any())
                } coAnswers {
                    delay(1000L)
                    postmonDTO
                }

                coEvery {
                    openCepApiService.getAddress(any())
                } coAnswers {
                    delay(3000L)
                    openCepDTO
                }

                coEvery {
                    widnetApiService.getAddress(any())
                } coAnswers {
                    delay(4000L)
                    widnetDTO
                }
            }

            is Winner.Widnet -> {
                coEvery { viaCepApiService.getAddress(any()) } coAnswers {
                    delay(4000L)
                    viaCepDTO
                }

                coEvery {
                    postmonApiService.getAddress(any())
                } coAnswers {
                    delay(2000L)
                    postmonDTO
                }

                coEvery {
                    openCepApiService.getAddress(any())
                } coAnswers {
                    delay(3000L)
                    openCepDTO
                }

                coEvery {
                    widnetApiService.getAddress(any())
                } coAnswers {
                    delay(1000L)
                    widnetDTO
                }
            }
        }


        addressRepositoryImplTest = AddressRepositoryImpl(
            viaCepApiService, openCepApiService, postmonApiService, widnetApiService
        )

        useCaseImpl = GetAddressUseCaseImpl(addressRepositoryImplTest)
    }

    private sealed class Winner {
        class ViaCep : Winner()
        class OpenCep : Winner()
        class Postmom : Winner()
        class Widnet : Winner()

    }

}