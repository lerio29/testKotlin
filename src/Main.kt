import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.io.FileReader

// runBlocking permet de lancer des coroutines vi le mot cle launch
fun main(args: Array<String>) = runBlocking {

  // extrait de http://kotlination.com/kotlin/kotlin-convert-object-to-from-json-gson

  val gson = GsonBuilder().setPrettyPrinting().create();

  for ((index, value) in args.withIndex()) {
    println("on boucle, la valeur est :" + value + " et son index est : " + index)
  }

  //liste des fichiers JSON
  val jsonFiles: List<String> = listOf("albums.json", "comments.json", "posts.json");


  //on parcours les fichiers json
  for ((index, value) in jsonFiles.withIndex()) {
    println("Traitement du fichier : " + value + ", dont l' index est : " + index);

    // traitement async des fichiers json
    when (value) {
      "albums.json" -> launch { albumsHandler(value, gson) }
      "comments.json" -> launch { commentsHandler(value, gson) }
      "posts.json" -> launch { postsHandler(value, gson) }
      else -> print("no case found for value : " + value)
    }


  }


  val res: String = sayHaveFun("MEC");
  println(res);

  val lambda = { a: Int, b: Int -> a * b };

  println(lambda(5, 2));


  val chainToManipulate: String = "une Chaine à Manipuler pour faire des tests !";
  var spaces: Int = 0;
  chainToManipulate.map { x -> if (x.isWhitespace()) spaces++ }
  println("nombre d'espaces : " + spaces);


  val blackList: List<String> = listOf("pouf", "pif", "paf");
  for ((index, value) in blackList.withIndex()) {
    println("on boucle, la valeur est :" + value + " et son index est : " + index)
  }

  val user: User = User("toto", "toti", 22);
  user.email = "toto@mail.com";
//  user.pass = "passw";
  println(user.toString());


}

/**
 * Methode de traitement des entrées json en asynchrone
 */
suspend fun albumsHandler(file: String, gson: Gson) {

  lateinit var fr: FileReader;

  //sampleStart

  // launch new coroutine and keep a reference to its Job
  fr = FileReader("./assets/" + file);

  try {
    var albums: List<Album> = gson.fromJson(fr, object : TypeToken<List<Album>>() {}.type);

    //on filtre et on affiche
    albums = albums.filter { al -> al.userId.equals(5) };
    albums.forEach { album -> println(album) };

  } catch (e: Exception) {
    println(e.message)
  }


//sampleEnd    
}

/**
 * Methode de traitement des entrées json en asynchrone
 */
suspend fun commentsHandler(file: String, gson: Gson) {

  lateinit var fr: FileReader;

  //sampleStart

  // launch new coroutine and keep a reference to its Job
  fr = FileReader("./assets/" + file);

  try {
    var comments: List<Comment> = gson.fromJson(fr, object : TypeToken<List<Comment>>() {}.type);

    //on filtre et on affiche
    comments = comments.filter { com -> com.postId.equals(5) };
    comments.forEach { comment -> println(comment) };

  } catch (e: Exception) {
    println(e.message)
  }


//sampleEnd    
}

/**
 * Methode de traitement des entrées json en asynchrone
 */
suspend fun postsHandler(file: String, gson: Gson) {

  lateinit var fr: FileReader;

  //sampleStart

  // launch new coroutine and keep a reference to its Job
  fr = FileReader("./assets/" + file);

  try {
    var posts: List<Album> = gson.fromJson(fr, object : TypeToken<List<Post>>() {}.type);

    //on filtre et on affiche
    posts = posts.filter { po -> po.userId.equals(5) };
    posts.forEach { post -> println(post) };

  } catch (e: Exception) {
    println(e.message)
  }

//sampleEnd    
}


fun sayHaveFun(param: String): String {
  val rep: String = "Have FUN " + param;
  println(rep);
  return "return : " + rep;
}


  

