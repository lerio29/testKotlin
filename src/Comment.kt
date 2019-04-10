import kotlinx.serialization.*

@Serializable
data class Comment(val postId :Int, val id :Int, var name :String, var email :String, var body :String) {
}