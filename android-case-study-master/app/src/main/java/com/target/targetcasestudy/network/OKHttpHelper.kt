package com.target.targetcasestudy.network

import android.util.Log

import com.target.targetcasestudy.BuildConfig

import java.security.cert.CertificateException

import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object OKHttpHelper {

    private var retrofit: Retrofit? = null
    private var mBaseUrl = ""


    fun getAPIService(baseUrl: String): APIService {
        return getClient(baseUrl).create(APIService::class.java)
    }

    fun getClient(baseUrl: String): Retrofit {

        if (retrofit == null || !baseUrl.equals(mBaseUrl, ignoreCase = true)) {
            Log.e("RetrofitClient", "baseUrl  === $baseUrl")
            mBaseUrl = baseUrl

            //TODO for logging purpose
            val logging = HttpLoggingInterceptor()

            logging.level = HttpLoggingInterceptor.Level.BODY
            //OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            //TODO: need to check once VPN is connected with this app
            //OkHttpClient httpClient = createOkHttpClient();

            //BYPASSING HTTPS
            val httpClientBuilder = OkHttpClient.Builder()
            val httpClient = configureToIgnoreCertificate(httpClientBuilder)

            // add logging as last interceptor
            if (BuildConfig.DEBUG) {
                httpClient!!.addInterceptor(logging)  // <-- this is the important line!
            }


            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient!!.build())  //to add logging code
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        }
        return retrofit!!
    }


    /**
     * For bypassing SSL certificate validation
     *
     * @param builder
     * @return builder
     */
    fun configureToIgnoreCertificate(builder: OkHttpClient.Builder): OkHttpClient.Builder? {
        try {
            /*Create a trust manager that does not validate certificate chains*/
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                @Throws(CertificateException::class)
                override fun checkClientTrusted(
                    chain: Array<java.security.cert.X509Certificate>,
                    authType: String
                ) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(
                    chain: Array<java.security.cert.X509Certificate>,
                    authType: String
                ) {
                }

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                    return arrayOf()
                }
            })

            /*Install the all-trusting trust manager*/
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())
            /*Create an ssl socket factory with our all-trusting manager*/
            val sslSocketFactory = sslContext.socketFactory
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier { hostname, session -> true }
        } catch (e: Exception) {
            Log.e("Exception", "Exception configureToIgnoreCertificate()")
        }

        return builder
    }

}
