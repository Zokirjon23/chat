package uz.example.chat.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.example.chat.usescase.*
import uz.example.chat.usescase.impl.*

@Module
@InstallIn(ViewModelComponent::class)
interface UsesCaseModule {

    @Binds
    fun contactUsesCase(usesCase: ContactUsesCaseImpl): ContactUsesCase

    @Binds
    fun dashboardUsesCase(usesCase: DashboardUsesCaseImpl): DashboardUsesCase

    @Binds
    fun startUsesCase(usesCase: StartScreenUsesCaseImpl): StartScreenUsesCase

    @Binds
    fun authUsesCase(usesCase: AuthScreenUsesCaseImpl): AuthScreenUsescase

    @Binds
    fun userRegUsesCase(usesCase: UserRegUsesCaseImpl): UserRegUsesCase

    @Binds
    fun containerUsesCase(usesCase: ContainerUsesCaseImpl): ContainerUsesCase

}