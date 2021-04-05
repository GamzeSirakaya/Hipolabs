import com.google.gson.annotations.SerializedName


data class Root (

	@SerializedName("company") val company : String,
	@SerializedName("team") val team : String,
	@SerializedName("members") val members : List<Member>
)