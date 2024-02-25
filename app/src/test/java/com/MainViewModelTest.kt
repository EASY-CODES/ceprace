package com

import com.engedu.ceprace.domain.model.AddressVO
import com.engedu.ceprace.initializer.CepRaceInitImpl
import com.example.cepraceapp.MainViewModel
import com.example.cepraceapp.util.ViewState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test


class MainViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    //sut
    private lateinit var mainViewModel: MainViewModel

    //mock
    private val cepRaceInit: CepRaceInitImpl = mockk()

    //fake
    private val vo = AddressVO("VIA CEP", "avenida cd 18", "marilucy", "tucurui", "pa")


    @Test
    fun executeSuccessReturnViewStateSuccess() = runTest {
        //Arrange
        coEvery { cepRaceInit.execute(any()) } returns vo //stub
        mainViewModel = MainViewModel(cepRaceInit)

        //Action
        mainViewModel.getAddress("68459370")

        //Assert
        Assert.assertEquals(ViewState.Success(vo), mainViewModel.addressState.value)
    }


    @Test
    fun executeErrorReturnViewStateError() = runTest {
        //Arrange
        coEvery { cepRaceInit.execute(any()) } throws Exception() //stub
        mainViewModel = MainViewModel(cepRaceInit)

        //Action
        mainViewModel.getAddress("68459370")

        //Assert
        Assert.assertEquals(
            ViewState.Error(
                error = "Parece que ninguém cruzou a linha de chagada =(",
                tryAgain = mainViewModel::tryAgain
            ), mainViewModel.addressState.value
        )
    }
}
