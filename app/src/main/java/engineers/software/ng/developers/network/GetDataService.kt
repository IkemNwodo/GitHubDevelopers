package engineers.software.ng.developers.network

import engineers.software.ng.developers.model.Developers
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GetDataService {

    @GET("/search/users")
    fun getDeveloperList(@Query("q")filter: String): Call<Developers>
}