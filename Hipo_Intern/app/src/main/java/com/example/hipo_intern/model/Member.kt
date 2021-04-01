import com.google.gson.annotations.SerializedName


data class Member (

	@SerializedName("name") val name : String,
	@SerializedName("age") val age : Int,
	@SerializedName("location") val location : String,
	@SerializedName("github") val github : String,
	@SerializedName("hipo") val hipo : Hipo
)