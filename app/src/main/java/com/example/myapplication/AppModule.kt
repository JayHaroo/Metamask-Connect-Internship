package com.example.myapplication

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.metamask.androidsdk.*


@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {

    /*
        This function provides a DappMetadata object as a dependency for dependency injection.
        It extracts the application name from the context and uses it to construct the DApp's name, URL,
        and a static icon URL. The metadata is used to describe the DApp when interacting with wallets or
        blockchain services.
     */
     @Provides
         fun provideDappMetadata(@ApplicationContext context: Context): DappMetadata {
             return DappMetadata(
                 name = context.applicationInfo.name,
                 url = "https://${context.applicationInfo.name}.com",
                 iconUrl = "https://cdn.sstatic.net/Sites/stackoverflow/Img/apple-touch-icon.png"
             )
         }

    /*
        This function provides an instance of EthereumFlow, a wrapper around the Ethereum SDK.
        It initializes the SDK with the application context, DApp metadata, and specific options
        like the infuraAPIKey for connecting to the Ethereum network. It ensures that EthereumFlow
        is properly configured for use in the app.
     */
     @Provides
         fun provideEthereumFlow(@ApplicationContext context: Context, dappMetadata: DappMetadata): EthereumFlow {
             return EthereumFlow(
                 Ethereum(
                     context,
                     dappMetadata,
                     SDKOptions(infuraAPIKey = BuildConfig.MY_INFURA_KEY)
                 )
             )
         }
}